package com.artist.wea.api.service.Impl;

import com.artist.wea.api.service.ArtistService;
import com.artist.wea.common.exception.CommonApiException;
import com.artist.wea.common.exception.errorcode.CommonErrorCode;
import com.artist.wea.common.util.FileValidator;
import com.artist.wea.db.dto.request.artist.AddPostReqDTO;
import com.artist.wea.db.dto.response.artist.AddPostResDTO;
import com.artist.wea.db.entity.Artist;
import com.artist.wea.db.entity.ArtistImg;
import com.artist.wea.db.entity.ArtistMember;
import com.artist.wea.db.entity.User;
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
import java.util.List;
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
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));

        Artist artist = Artist.builder()
                .name(addPostReqDto.getName())
                .simple(addPostReqDto.getSimple())
                .introduce(addPostReqDto.getIntroduce())
                .area(addPostReqDto.getArea())
                .activated(true)
                .build();
        artistRepository.save(artist);
        artistMemberRepository.save(new ArtistMember(user, artist, true));

        AddPostResDTO addPostResDto = AddPostResDTO.builder()
                .name(addPostReqDto.getName())
                .simple(addPostReqDto.getSimple())
                .introduce(addPostReqDto.getIntroduce())
                .area(addPostReqDto.getArea())
                .build();

        return addPostResDto;
    }

    /**
     * 아티스트 등록 해제(탈퇴)
     */
    @Override
    public void deleteArtist(User user, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new CommonApiException(CommonErrorCode.ARTIST_NOT_FOUND));
        ArtistMember artistMember = artistMemberRepository.findByUserAndArtist(user, artist);
        artistMemberRepository.delete(artistMember);
    }

    /**
     * 아티스트 멤버 초대
     */
    @Override
    public void inviteArtist(User user, String userId) {
        User inviteUser = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));
        Artist artist = artistMemberRepository.findByUser(user).getArtist();
        ArtistMember artistMember = artistMemberRepository.findByUserAndArtist(inviteUser, artist);
        if (artistMember != null) throw new CommonApiException(CommonErrorCode.ARTIST_DUPLICATE_MEMBER);
        artistMemberRepository.save(new ArtistMember(inviteUser, artist, false));
    }

    /**
     * 아티스트 멤버 초대 요청 수락
     */
    @Override
    public void acceptArtist(User user, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new CommonApiException(CommonErrorCode.ARTIST_NOT_FOUND));
        ArtistMember artistMember = artistMemberRepository.findByUserAndArtist(user, artist);
        artistMember.setActivated(true);
        artistMemberRepository.save(artistMember);
    }

    /**
     * 아티스트 멤버 초대 요청 거절
     */
    @Override
    public void refuseArtist(User user, Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new CommonApiException(CommonErrorCode.ARTIST_NOT_FOUND));
        ArtistMember artistMember = artistMemberRepository.findByUserAndArtist(user, artist);
        artistMemberRepository.delete(artistMember);
    }

    /**
     * 아티스트 프로필 수정
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
    public List<Artist> searchArtist(String artistName) {
        return artistRepository.findByNameContaining(artistName);
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
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));
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
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));
        Artist artist = artistMemberRepository.findByUser(user).getArtist();
        ArtistImg artistImg = artistImgRepository.findById(artist.getArtistImg().getId()).get();

        artist.setArtistImg(null);
        File file = new File(artistImg.getFileUrl());
        file.delete();

        artistImgRepository.deleteById(artistImg.getId());
    }

}
