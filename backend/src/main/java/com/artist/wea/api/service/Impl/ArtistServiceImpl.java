package com.artist.wea.api.service.Impl;

import com.artist.wea.api.service.ArtistService;
import com.artist.wea.common.util.FileValidator;
import com.artist.wea.db.dto.request.artist.AddPostReqDTO;
import com.artist.wea.db.dto.response.artist.AddPostResDTO;
import com.artist.wea.db.entity.*;
import com.artist.wea.db.repository.ArtistImgRepository;
import com.artist.wea.db.repository.ArtistMemberRepository;
import com.artist.wea.db.repository.ArtistRepository;
import com.artist.wea.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final ArtistMemberRepository artistMemberRepository;
    private final ArtistImgRepository artistImgRepository;
    @Value("${file.path}")
    private String path;

    /**
     * 아티스트 등록
     */
    @Override
    public AddPostResDTO addArtist(String userId, AddPostReqDTO addPostReqDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Artist artist = Artist.builder()
                .name(addPostReqDto.getName())
                .simple(addPostReqDto.getSimple())
                .introduce(addPostReqDto.getIntroduce())
                .area(addPostReqDto.getArea())
                .build();
        artistRepository.save(artist);
        artistMemberRepository.save(new ArtistMember(user, artist));

        AddPostResDTO addPostResDto = AddPostResDTO.builder()
                .name(addPostReqDto.getName())
                .simple(addPostReqDto.getSimple())
                .introduce(addPostReqDto.getIntroduce())
                .area(addPostReqDto.getArea())
                .build();

        return addPostResDto;
    }

    /**
     * 아티스트 정보 수정
     */
    @Override
    public Artist updateArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    /**
     * 이미지 파일 유효성 검증
     */
    @Override
    public boolean validImgFile(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (!multipartFile.isEmpty()) {
                boolean isValid = FileValidator.validImgFile(inputStream);
                if (!isValid) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Optional<Artist> getArtist(User user) {
        return artistRepository.findById(artistMemberRepository.findByUser(user).getArtist().getId());
    }

    /**
     * 프로필 이미지 조회
     */
    @Override
    public ArtistImg getImage(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Artist artist = artistMemberRepository.findByUser(user).getArtist();
        ArtistImg artistImg = (artist.getArtistImg() != null) ? artistImgRepository.findById(artist.getArtistImg().getId()).get() : null;
        return artistImg;
    }

    /**
     * 프로필 이미지 업로드
     */
    public ArtistImg uploadImage(MultipartFile multipartFile) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileUrl = path + uuid + "_" + multipartFile.getOriginalFilename();
        ArtistImg artistImg = artistImgRepository.save(
                ArtistImg.builder()
                        .name(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .fileUrl(fileUrl)
                        .build()
        );
        multipartFile.transferTo(new File(fileUrl));

        return artistImg;
    }

    /**
     * 프로필 이미지 수정
     */
    public ArtistImg updateImage(MultipartFile multipartFile, Artist artist) throws IOException {
        UUID uuid = UUID.randomUUID();
        String filePath = path + uuid + "_" + multipartFile.getOriginalFilename();

        ArtistImg artistImg = artistImgRepository.findById(artist.getArtistImg().getId()).get();

        File file = new File(artistImg.getFileUrl());
        file.delete();

        artistImg.updateArtistImg(multipartFile, filePath);
        artistImgRepository.save(artistImg);
        multipartFile.transferTo(new File(filePath));

        return artistImg;
    }

    /**
     * 프로필 이미지 삭제
     */
    public void deleteImage(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Artist artist = artistMemberRepository.findByUser(user).getArtist();
        ArtistImg artistImg = artistImgRepository.findById(artist.getArtistImg().getId()).get();

        artist.setArtistImg(null);
        File file = new File(artistImg.getFileUrl());
        file.delete();

        artistImgRepository.deleteById(artistImg.getId());
    }

}
