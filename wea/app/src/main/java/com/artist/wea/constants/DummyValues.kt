package com.artist.wea.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.artist.wea.R
import com.artist.wea.components.data.AlarmData
import com.artist.wea.components.data.ArtistRankData
import com.artist.wea.data.ArtistInfo
import com.artist.wea.data.ConcertInfo
import com.artist.wea.data.PaymentInfo
import com.artist.wea.data.TicketInfo
import java.time.LocalDateTime

class DummyValues {

    val aritstSearchList = arrayOf<ArtistInfo>(
        ArtistInfo(
            id = 1,
            profileImgURL = "https://image.kmib.co.kr/online_image/2014/1015/201410152053_61170008765071_1.jpg",
            bgImgURL = "https://img.hankyung.com/photo/202205/01.29843403.1.jpg",
            artistName = "ENJOY",
            comment = "안녕하세요, 행복을 노래하는 가수입니다.",
            mainIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                    "\n" +
                    "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                    "\n" +
                    "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                    "\n" +
                    "음악을 사랑한다는 마음 하나라면,\n" +
                    "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                    "\n" +
                    "창립일\n" +
                    "2013. 01. 16.\n" +
                    "\n" +
                    "자주 출몰하는 장소\n" +
                    "홍대입구 2번 출구, 강남역 3번 출구\n" +
                    "\n" +
                    "인스타그램\n" +
                    "@abc_123_heart",
        ),
        ArtistInfo(
            id = 2,
            profileImgURL = "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
            artistName = "무슨무슨 아티스트",
            comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
        ),
        ArtistInfo(
            id = 3,
            profileImgURL= "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
            artistName = "쏼라쏼라 아티스트",
            comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
        ),
        ArtistInfo(
            id = 4,
            profileImgURL= "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
            artistName = "블라블라 아티스트",
            comment = "좋은 음악 함께 공유해요"
        ),
        ArtistInfo(
            id = 5,
            profileImgURL ="https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
            artistName = "무슨무슨 아티스트",
            comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
        ),
        ArtistInfo(
            id = 6,
            profileImgURL= "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
            artistName = "쏼라쏼라 아티스트",
            comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
        ),
        ArtistInfo(
            id = 7,
            profileImgURL= "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
            artistName = "블라블라 아티스트",
            comment = "좋은 음악 함께 공유해요"
        ),
        ArtistInfo(
            id = 8,
            profileImgURL= "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
            artistName = "무슨무슨 아티스트",
            comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
        ),
        ArtistInfo(
            id = 9,
            profileImgURL= "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
            artistName = "쏼라쏼라 아티스트",
            comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
        )
    )

    val memberList = arrayOf<ArtistInfo>(
        ArtistInfo(
            profileImgURL = "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
            artistName = "블라블라 아티스트",
            comment = "좋은 음악 함께 공유해요"
        ),
        ArtistInfo(
            profileImgURL = "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
            artistName = "무슨무슨 아티스트",
            comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
        ),
        ArtistInfo(
            profileImgURL = "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
            artistName = "쏼라쏼라 아티스트",
            comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
        )
    )

    val rankList = mutableListOf<ArtistRankData>(
        ArtistRankData(
            no = 1,
            artistName = "무슨무슨 아티스트",
            artistAddress = "경기 고양시 일산서구",
            artistImgURL = "https://cdnimage.dailian.co.kr/news/202002/news_1581993821_869287_m_1.jpeg"
        ),
        ArtistRankData(
            no = 2,
            artistName = "블라블라 아티스트",
            artistAddress = "서울 특별시 강남구",
            artistImgURL = "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2Z3MD7RDMQIRSUWM5ASEVKSKAY.jpg"

        ),
        ArtistRankData(
            no = 3,
            artistName = "룰라룰라 아티스트",
            artistAddress = "인천광역시 서구",
            artistImgURL = "https://www.kyongbuk.co.kr/news/photo/202105/2076224_500129_3236.jpg"
        ),
        ArtistRankData(
            no = 4,
            artistName = "훌라훌라 아티스트",
            artistAddress = "경기 안산시 단원구",
            artistImgURL = "https://cdn.dkilbo.com/news/photo/202103/328795_222527_1909.jpg"
        ),
        ArtistRankData(
            no = 5,
            artistName = "눈누난나 아티스트",
            artistAddress = "경기 수원시 권선구",
            artistImgURL = "https://news.tbs.seoul.kr/Upload/Image/20221202/00000000000001308345.jpg"
        ),
        ArtistRankData(
            no = 6,
            artistName = "우리동네 아티스트",
            artistAddress = "서을 특별시 관악구",
            artistImgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyemqDmEPapQHDeVBwwFuMZeTkp97FvTryJA&usqp=CAU"
        )
    )

    @Composable
    fun getAlarmList():MutableList<AlarmData> {
        return  mutableListOf<AlarmData>(
            AlarmData(
                iconImg = Icons.Filled.Notifications,
                content = "개인정보 처리 방침이 변경되었습니다.",
                isChek = false,
                alarmColor = colorResource(id = R.color.pastel_pink100),
                contentColor = colorResource(id = R.color.red500)
            ),
            AlarmData(
                iconImg = Icons.Filled.Info,
                content = "무슨무슨 공연 후원에 성공했습니다. 이제 티켓 페이지에서 공연 티켓을 확인할 수 있습니다.",
                isChek = false,
                alarmColor = colorResource(id = R.color.mono100),
                contentColor = colorResource(id = R.color.mono600)
            ),
            AlarmData(
                iconImg = Icons.Filled.CheckCircle,
                content = "무슨무슨 아티스트님이 공연 후기를 남겼어요 지금 바로 확인해보세요!",
                isChek = false,
                alarmColor = colorResource(id = R.color.sky_blue100),
                contentColor = colorResource(id = R.color.sky_blue600)
            ),
            AlarmData(
                iconImg = Icons.Filled.Email,
                content = "우리동네 아티스트 팀에서 보내온 초대가 도착했어요. 수락하시겠습니까?",
                isChek = true,
                alarmColor = colorResource(id = R.color.pastel_yellow100),
                contentColor = colorResource(id = R.color.brown700),
                checkColor = colorResource(id = R.color.pastel_green200)
            )
        )
    }

    val paymentInfoList = listOf<PaymentInfo>(
        PaymentInfo(
            name = "카카오 페이",
            paymentImgURL = "https://play-lh.googleusercontent.com/Ob9Ys8yKMeyKzZvl3cB9JNSTui1lJwjSKD60IVYnlvU2DsahysGENJE-txiRIW9_72Vd"
        ),
        PaymentInfo(
            name = "네이버 페이",
            paymentImgURL = "https://mir-s3-cdn-cf.behance.net/project_modules/1400/3393f738210507.575900b317fb4.png"
        ),
    )

    val concertList =  arrayOf<ConcertInfo>(
        ConcertInfo(
            imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
            artistName = "무슨무슨 아티스트",
            concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
        ),
        ConcertInfo(
            imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
            artistName = "블라블라 아티스트",
            concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
            location = "서울 서대문구 신촌역로 17 GS 25 앞"
        ),
        ConcertInfo(
            imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
            artistName = "울라울라 아티스트",
            concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
            location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
        ),
        ConcertInfo(
            imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
            artistName = "무슨무슨 아티스트",
            concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
        ),
        ConcertInfo(
            imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
            artistName = "블라블라 아티스트",
            concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
            location = "서울 서대문구 신촌역로 17 GS 25 앞"
        ),
        ConcertInfo(
            imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
            artistName = "울라울라 아티스트",
            concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
            location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
        ),
        ConcertInfo(
            imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
            artistName = "무슨무슨 아티스트",
            concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
        ),
        ConcertInfo(
            imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
            artistName = "블라블라 아티스트",
            concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
            location = "서울 서대문구 신촌역로 17 GS 25 앞"
        ),
        ConcertInfo(
            imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
            artistName = "울라울라 아티스트",
            concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
            location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
        )
    )


    val ticketInfoList = listOf<TicketInfo>(
        TicketInfo(
            serialNo = "1234-5678-1234",
            concertName = "무슨무슨 버스킹",
            teamName = "버스킹과 아이들",
            dateTime = LocalDateTime.now(),
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            isOnAir = true,
        ),
        TicketInfo(
            serialNo = "1234-5678-1234",
            concertName = "무슨무슨 버스킹",
            teamName = "버스킹과 아이들",
            dateTime = LocalDateTime.now(),
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
        ),
        TicketInfo(
            serialNo = "1234-5678-1234",
            concertName = "무슨무슨 버스킹",
            teamName = "버스킹과 아이들",
            dateTime = LocalDateTime.now(),
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
        ),
        TicketInfo(
            serialNo = "1234-5678-1234",
            concertName = "무슨무슨 버스킹",
            teamName = "버스킹과 아이들",
            dateTime = LocalDateTime.now(),
            location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
        )
    )
}