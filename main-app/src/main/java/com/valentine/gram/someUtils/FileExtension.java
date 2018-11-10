package com.valentine.gram.controller;

import java.util.ArrayList;
import java.util.List;

public class FileExtension {

    /**
     * short list of video file extensions
     * @return
     */
    private static List<String> videoExtensions() {

        List<String> lists = new ArrayList<>();
        lists.add("mp4");
        lists.add("m3u");
        lists.add("m3u8");
        lists.add("m4a");
        lists.add("m4p");
        lists.add("m4b");
        lists.add("m4r");
        lists.add("m4v");
        return lists;
    }

    private static List<String> videoExtList = new ArrayList<>(videoExtensions());

    /**
     * matcher for videos
     * @param ext
     * @return
     */
    static boolean matchVideoExt(String ext){
        return videoExtList.stream().anyMatch(ext::matches);
    }


    /**
     * short list of image extensions
     * @return
     */
    private static List<String> imageExtensions() {
        List<String> lists = new ArrayList<>();
        lists.add("png");
        lists.add("jpeg");
        lists.add("tiff");
        lists.add("gif");
        lists.add("bmp");
        lists.add("jfif");
        return lists;
    }

    private static List<String> imageExtList = new ArrayList<>(imageExtensions());

    /**
     * matcher for images
     * @param ext
     * @return
     */
    static boolean matchImageExt(String ext){
        return imageExtList.stream().anyMatch(ext::matches);
    }
}
