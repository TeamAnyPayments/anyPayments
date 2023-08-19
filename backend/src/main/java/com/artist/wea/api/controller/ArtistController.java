package com.artist.wea.api.controller;

import com.artist.wea.api.service.ArtistService;
import com.artist.wea.config.security.UserPrincipal;
import com.artist.wea.db.dto.request.artist.AddPostReqDTO;
import com.artist.wea.db.dto.util.ResponseDTO;
import com.artist.wea.db.entity.Artist;
import com.artist.wea.db.entity.ArtistImg;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.UserImg;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    /**
     * 아티스트 등록 API
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> addArtist(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddPostReqDTO addPostReqDTO) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "회원 가입 완료", artistService.addArtist(userPrincipal.getUsername(), addPostReqDTO)));
    }

    /**
     * 아티스트 프로필 이미지 조회 API
     */
    @GetMapping("/image")
    public ResponseEntity<?> getImage(@AuthenticationPrincipal UserPrincipal userPrincipal) throws IOException {
        ArtistImg artistImg = artistService.getImage(userPrincipal.getUsername());
        if(artistImg==null) return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "등록된 프로필 이미지가 없습니다."));

        Resource resource = new FileSystemResource(artistImg.getFileUrl());
        HttpHeaders header = new HttpHeaders();
        Path filePath = Paths.get(artistImg.getFileUrl());
        header.add("Content-Type", Files.probeContentType(filePath));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    /**
     * 아티스트 프로필 이미지 업로드 API
     */
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (artistService.validImgFile(multipartFile)) {
            Artist artist = artistService.getArtist(userPrincipal.getUser()).get();
            ArtistImg artistImg = artistService.uploadImage(multipartFile);
            artist.setArtistImg(artistImg);
            artistService.updateArtist(artist);
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "프로필 이미지 업로드 성공"));
        }
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "올바른 이미지 파일이 아닙니다"));
    }

    /**
     * 아티스트 프로필 이미지 수정 API
     */
    @PutMapping("/image")
    public ResponseEntity<?> updateImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (artistService.validImgFile(multipartFile)) {
            Artist artist = artistService.getArtist(userPrincipal.getUser()).get();
            artistService.updateImage(multipartFile, artist);
            artistService.updateArtist(artist);
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "프로필 이미지 업데이트 성공"));
        }
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "올바른 이미지 파일이 아닙니다"));
    }

    /**
     * 아티스트 프로필 이미지 삭제 API
     */
    @DeleteMapping("/image")
    public ResponseEntity<?> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        artistService.deleteImage(userPrincipal.getUsername());
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK,  "프로필 이미지 삭제 성공"));
    }

}