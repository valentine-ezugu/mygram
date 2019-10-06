package com.valentine.service;

import com.valentine.dao.PhotosRepository;
import com.valentine.model.Photo;
import com.valentine.model.User;
import com.valentine.service.exception.MyResourceNotFoundException;
import com.valentine.service.exception.MyValidationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.net.URL;

@AllArgsConstructor
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private PhotosRepository photosRepository;

    private AwsFileStorage fileStorage;

    private FileStorageHelper fileStorageHelper;

    @Override
    public Photo savePhoto(MultipartFile file) throws IOException {
        checkIfImageElseThrowError(file);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Photo photo = fileStorageHelper.preparePhoto(fileStorage, file, user, true);
        return photosRepository.save(photo);
    }

    @Override
    public void deletePhotoById(Integer id) {

        photosRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Photo getPhotoById(Integer id) throws DataAccessException {
        return photosRepository.findById(id).orElseThrow(()
            -> new MyResourceNotFoundException(String.format("photo with id %d not found", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public URL getPhotoDownloadURL(Integer id) throws DataAccessException {
        Photo photo = getPhotoById(id);
        return FileStorageHelper.getDownloadUrl(fileStorage, photo);
    }

    @Override
    public void deleteArtifact(Photo photo) throws DataAccessException {
        photosRepository.delete(photo);
    }

    private void checkIfImageElseThrowError(MultipartFile file) {
        String mimeType = file.getContentType();
        String type = mimeType.split("/")[0];
        if ( !(type.equalsIgnoreCase("image") || type.equalsIgnoreCase("video")) ) {
            throw new MyValidationException("image_or_video");
        }
    }

}
