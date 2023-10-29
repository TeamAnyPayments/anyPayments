package com.artist.wea;

import com.artist.wea.db.dto.response.artist.AddPostResDTO;
import com.artist.wea.db.entity.Artist;
import com.artist.wea.db.entity.ArtistMember;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.base.Role;
import com.artist.wea.db.repository.ArtistMemberRepository;
import com.artist.wea.db.repository.ArtistRepository;
import com.artist.wea.db.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArtistIndexTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void testWithIndex() {
        System.out.println("인덱스 사용한 경우");
        long startTime = System.nanoTime();
        String name = "artist_name1";
        artistRepository.findByNameContaining(name);
        long endTime = System.nanoTime();
        System.out.println("Time taken with index: " + (endTime - startTime) + " ns");
    }

//    @Test
//    public void testWithoutIndex() {
//        System.out.println("인덱스 사용하지 않은 경우");
//        long startTime = System.nanoTime();
//        String name = "냐아앙";
//        List<Artist> allArtists = artistRepository.findAll();
//        List<Artist> matchingUsers = allArtists.stream()
//                .filter(artist -> artist.getName().equals(name))
//                .collect(Collectors.toList());
//        long endTime = System.nanoTime();
//        System.out.println("Time taken with index: " + (endTime - startTime) + " ns");
//    }

}
