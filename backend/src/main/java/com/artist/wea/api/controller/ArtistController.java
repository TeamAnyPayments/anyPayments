package com.artist.wea.api.controller;

import com.artist.wea.api.service.ArtistService;
import com.artist.wea.config.security.UserPrincipal;
import com.artist.wea.db.dto.request.artist.AddPostReqDTO;
import com.artist.wea.db.dto.request.artist.UpdatePutReqDTO;
import com.artist.wea.db.dto.util.ResponseDTO;
import com.artist.wea.db.entity.Artist;
import com.artist.wea.db.entity.ArtistImg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "아티스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    /**
     * 아티스트 등록 API
     */
    @Operation(summary = "아티스트 등록 API")
    @PostMapping
    public ResponseEntity<ResponseDTO> addArtist(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddPostReqDTO addPostReqDTO) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아티스트 등록 완료", artistService.addArtist(userPrincipal.getUsername(), addPostReqDTO)));
    }

    /**
     * 아티스트 등록 해제 API
     */
    @Operation(summary = "아티스트 등록 해제 API")
    @DeleteMapping
    public ResponseEntity<?> deleteArtist(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        artistService.deleteArtist(userPrincipal.getUser());
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아티스트 등록 해제"));
    }

    /**
     * 아티스트 프로필 수정 API
     */
    @Operation(summary = "아티스트 프로필 수정 API")
    @PutMapping
    public ResponseEntity<ResponseDTO> updateArtist(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody UpdatePutReqDTO updatePutReqDTO) {
        Artist artist = artistService.getArtist(userPrincipal.getUser()).get();
        artist.setSimple(updatePutReqDTO.getSimple());
        artist.setIntroduce(updatePutReqDTO.getIntroduce());
        artist.setArea(updatePutReqDTO.getArea());
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아티스트 프로필 수정 완료", artistService.updateArtist(artist)));
    }

    /**
     * 아티스트 프로필 이미지 조회 API
     */
    @Operation(summary = "아티스트 프로필 이미지 조회 API")
    @GetMapping("/image")
    public ResponseEntity<?> getImage(@AuthenticationPrincipal UserPrincipal userPrincipal) throws IOException {
        ArtistImg artistImg = artistService.getImage(userPrincipal.getUsername());
        if (artistImg == null) return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "등록된 프로필 이미지가 없습니다."));

        Resource resource = new FileSystemResource(artistImg.getFileUrl());
        HttpHeaders header = new HttpHeaders();
        Path filePath = Paths.get(artistImg.getFileUrl());
        header.add("Content-Type", Files.probeContentType(filePath));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    /**
     * 아티스트 프로필 이미지 업로드 API
     */
    @Operation(summary = "아티스트 프로필 이미지 업로드 API")
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
    @Operation(summary = "아티스트 프로필 이미지 수정 API")
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
    @Operation(summary = "아티스트 프로필 이미지 삭제 API")
    @DeleteMapping("/image")
    public ResponseEntity<?> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        artistService.deleteImage(userPrincipal.getUsername());
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "프로필 이미지 삭제 성공"));
    }

}
