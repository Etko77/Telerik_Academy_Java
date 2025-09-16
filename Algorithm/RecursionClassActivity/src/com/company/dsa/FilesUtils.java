package com.company.dsa;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FilesUtils {
    public static void traverseDirectories(String path) {
        traverseDir(new File(path),0);
    }

    private static void traverseDir(File file, int level) {
        if(!file.exists()) return;

        System.out.println(" ".repeat(level) + file.getName() + (file.isDirectory() ? ":" : ""));

        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null) return;

            for (File f : files){
                traverseDir(f, level+1);
            }
        }
    }

    public static List<String> findFiles(String path, String extension) {
        return find(new File(path), extension, new ArrayList<>());
    }

    private static List<String> find(File file, String extension, List<String> found) {
        if(!file.exists()) return found;

        if(!file.isDirectory() && file.getName().endsWith(extension)){
            found.add(file.getName());
        }

        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files == null) return found;

            for (File f : files){
                find(f,extension,found);
            }
        }
        return found;
    }


    public static boolean fileExists(String path, String fileName) {
        File file = new File(path);

        if(!file.exists()) return false;

        if(file.isDirectory()){
            File[] files = file.listFiles();
            if( files == null) return false;

            for(File f : files){
                if(f.getName().equals(fileName)){
                    return true;
                }
                if(f.isDirectory()){
                    if(fileExists(f.getAbsolutePath(),fileName)){
                        return true;
                    }
                }
            }

        }

       return false;

    }

    public static Map<String, Integer> getDirectoryStats(String path) {
        return generateMapForDirStats(new File(path), new TreeMap<String,Integer>() );
    }
    public static Map<String,Integer>  generateMapForDirStats(File file, TreeMap<String,Integer> treeMap){
        if(!file.exists()) return treeMap;

        if(!file.isDirectory()){
            String ext = getFileExtension(file.getName());
            treeMap.put(ext, treeMap.getOrDefault(ext,0)+1);
        }else{
            File[] files = file.listFiles();
            if(file == null) return treeMap;

            for(File f: files){
                generateMapForDirStats(f,treeMap);
            }
        }
        return treeMap;
    }
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

}
