package com.valentine.service;

import com.valentine.dao.PhotosRepository;
import com.valentine.model.Photo;
import com.valentine.model.User;
import com.valentine.service.exception.MyResourceNotFoundException;
import com.valentine.service.exception.MyValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

@AllArgsConstructor
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private PhotosRepository photosRepository;

    @Autowired
    private AwsFileStorage fileStorage;

    @Override
    public Photo savePhoto(MultipartFile file) throws IOException {
        checkIfImageElseThrowError(file);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Photo photo = prepareArtifact(fileStorage, file, user, true);
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
        return PrepareArtifact.getDownloadUrl(fileStorage, photo);
    }

    @Override
    public void deletePhoto(Photo photo) throws DataAccessException {
        photosRepository.delete(photo);
    }

    private void checkIfImageElseThrowError(MultipartFile file) {
        String mimeType = file.getContentType();
        String type = mimeType.split("/")[0];
        if ( !(type.equalsIgnoreCase("image") || type.equalsIgnoreCase("video")) ) {
            throw new MyValidationException("image_or_video");
        }
    }

    @Override
    public Photo prepareArtifact(AwsFileStorage fileStorage, MultipartFile file, User currentUser, boolean isPublic) throws IOException {
        Photo photo = new Photo();
        String url = fileStorage.saveFile(currentUser, file, file.getOriginalFilename());
        photo.setInitialName(file.getOriginalFilename());
        photo.setImage_path(url);
        photo.setImage_size((int)(file.getSize()));
        photo.setDate_created(LocalDate.now());
        photo.setUser(currentUser);
        return photo;
    }

    public void deletePhotoFromStorage(AwsFileStorage fileStorage, Photo photo) {
        String objectId = fileStorage.extractObjectIdFromResourceUrl(photo.getImage_path());
        fileStorage.deleteFile(objectId);
    }

}
