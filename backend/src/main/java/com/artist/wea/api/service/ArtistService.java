package com.artist.wea.api.service;

import com.artist.wea.db.dto.request.artist.AddPostReqDTO;
import com.artist.wea.db.dto.response.artist.AddPostResDTO;
import com.artist.wea.db.entity.Artist;
import com.artist.wea.db.entity.ArtistImg;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.UserImg;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ArtistService {

    AddPostResDTO addArtist(String userId, AddPostReqDTO addPostReqDto);

    Artist updateArtist(Artist artist);

    boolean validImgFile(MultipartFile multipartFile);
    Optional<Artist> getArtist(User user);

    ArtistImg getImage(String email);

    ArtistImg uploadImage(MultipartFile multipartFile) throws IOException;

    ArtistImg updateImage(MultipartFile multipartFile, Artist artist) throws IOException;

    void deleteImage(String email);

}
