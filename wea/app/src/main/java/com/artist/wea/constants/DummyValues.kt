package com.artist.wea.constants

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.artist.wea.R
import com.artist.wea.data.AlarmData
import com.artist.wea.data.ArtistInfo
import com.artist.wea.data.ArtistRankData
import com.artist.wea.data.ConcertInfo
import com.artist.wea.data.ConcertListInfo
import com.artist.wea.data.PaymentItemInfo
import com.artist.wea.data.TicketInfo
import java.time.LocalDateTime

class DummyValues {

    companion object {
        val artistSearchList = mutableMapOf<String, ArtistInfo>(
            "abc001" to ArtistInfo(
                userId = "abc001",
                profileImgURL = "https://image.kmib.co.kr/online_image/2014/1015/201410152053_61170008765071_1.jpg",
                bgImgURL = "https://img.hankyung.com/photo/202205/01.29843403.1.jpg",
                artistName = "로드 버스킹",
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

            Pair(
                "abc002",
                ArtistInfo(
                    userId = "abc002",
                    profileImgURL = "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                    artistName = "레트로 레벨 (Retro Level)",
                    comment = "레트로 팝, 디스코, 우리 함께 즐겨볼래요?",
                    mainIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                            "\n" +
                            "홍대 스트릿 버스커 그룹 레트로 레벨 (Retro Level) 입니다.\n" +
                            "\n" +
                            "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                            "\n" +
                            "음악을 사랑한다는 마음 하나라면,\n" +
                            "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                            "\n" +
                            "창립일\n" +
                            "2014. 03. 11.\n" +
                            "\n" +
                            "자주 출몰하는 장소\n" +
                            "홍대입구 2번 출구, 강남역 3번 출구\n" +
                            "\n" +
                            "인스타그램\n" +
                            "@retro_heart",
                )
            ),
            Pair(
                "abc003",
                ArtistInfo(
                    userId = "abc003",
                    profileImgURL = "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                    artistName = "로보틱 스트라이크 (Robotic Strike)",
                    comment = "Rock & Roll !! 일렉트로닉, 퓨처 베이스의 세계로 당신을 초대합니다."
                )
            ),
            Pair(
                "abc004",
                ArtistInfo(
                    userId = "abc004",
                    profileImgURL = "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                    artistName = "화이어파이트 (Fireflight)",
                    comment = "좋은 음악 함께 공유해요"
                )
            ),
            Pair(
                "abc005",
                ArtistInfo(
                    userId = "abc005",
                    profileImgURL = "https://media.istockphoto.com/id/1125877063/photo/mixed-race-woman-singing-and-playing-guitar.jpg?s=612x612&w=0&k=20&c=23unW_Ugni5lUvAY2nccGkxtWQ5FtkiWgRyyN6wZMFs=",
                    artistName = "루미노브스 (Luminovos)",
                    comment = "신스팝, 일렉트로닉의 전도사 입니다."
                )
            ),
            Pair(
                "abc006",
                ArtistInfo(
                    userId = "abc006",
                    profileImgURL = "https://media.istockphoto.com/id/1297166315/photo/group-of-carefree-friends-having-fun-while-commuting-by-public-transport.jpg?s=612x612&w=0&k=20&c=duvy-w7MbIFsV_pQCSRGB66hcJf-ZgU-aT5tI6ELuUw=",
                    artistName = "에코서클 (Echo Circle)",
                    comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
                )
            ),
            Pair(
                "abc007",
                ArtistInfo(
                    userId = "abc007",
                    profileImgURL = "https://cdn.pixabay.com/photo/2016/01/14/06/09/woman-1139397_640.jpg",
                    artistName = "리프리스트 (Refreest)",
                    comment = "레게, 댄스홀 그리고 당신..."
                )
            ),
            Pair(
                "abc008",
                ArtistInfo(
                    userId = "abc008",
                    profileImgURL = "https://media.gettyimages.com/id/1221351454/photo/portrait-of-a-japanese-guitar-player-at-home-studio.jpg?s=612x612&w=gi&k=20&c=ecFLVtyehqPqX9w2esmeBhpXbeBxsvUVJY0FibGrutU=",
                    artistName = "블루스피릿 (Bluespirit)",
                    comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
                )
            ),
            Pair(
                "abc009",
                ArtistInfo(
                    userId = "abc009",
                    profileImgURL = "https://musicindustryhowtoimages.s3.amazonaws.com/wp-content/uploads/2019/09/24183938/become-musician.jpg",
                    artistName = "크리스탈크래프트 (Crystal Craft)",
                    comment = "톡톡 튀는 신스웨이브 & 신스팝의 세계로 당신을 초대합니다."
                )
            )
        )


        val memberList = listOf<ArtistInfo>(
            ArtistInfo(
                userId = "abc005",
                profileImgURL = "https://media.istockphoto.com/id/1125877063/photo/mixed-race-woman-singing-and-playing-guitar.jpg?s=612x612&w=0&k=20&c=23unW_Ugni5lUvAY2nccGkxtWQ5FtkiWgRyyN6wZMFs=",
                artistName = "루미노브스 (Luminovos)",
                comment = "신스팝, 일렉트로닉의 전도사 입니다."
            ),
            ArtistInfo(
                userId = "abc009",
                profileImgURL = "https://musicindustryhowtoimages.s3.amazonaws.com/wp-content/uploads/2019/09/24183938/become-musician.jpg",
                artistName = "크리스탈크래프트 (Crystal Craft)",
                comment = "톡톡 튀는 신스웨이브 & 신스팝의 세계로 당신을 초대합니다."
            ),
            ArtistInfo(
                userId = "abc003",
                profileImgURL = "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                artistName = "로보틱 스트라이크 (Robotic Strike)",
                comment = "Rock & Roll !! 일렉트로닉, 퓨처 베이스의 세계로 당신을 초대합니다."
            )
        )

        val rankList = mutableListOf<ArtistRankData>(
            ArtistRankData(
                no = 1,
                artistId = "abc001",
                artistName = artistSearchList.get("abc001")?.artistName ?: "",
                artistAddress = "경기 고양시 일산서구",
                artistImgURL = "https://cdnimage.dailian.co.kr/news/202002/news_1581993821_869287_m_1.jpeg"
            ),
            ArtistRankData(
                no = 2,
                artistId = "abc002",
                artistName = artistSearchList.get("abc002")?.artistName ?: "",
                artistAddress = "서울 특별시 강남구",
                artistImgURL = "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2Z3MD7RDMQIRSUWM5ASEVKSKAY.jpg"

            ),
            ArtistRankData(
                no = 3,
                artistId = "abc003",
                artistName = artistSearchList.get("abc003")?.artistName ?: "",
                artistAddress = "인천광역시 서구",
                artistImgURL = "https://www.kyongbuk.co.kr/news/photo/202105/2076224_500129_3236.jpg"
            ),
            ArtistRankData(
                no = 4,
                artistId = "abc004",
                artistName = artistSearchList.get("abc004")?.artistName ?: "",
                artistAddress = "경기 안산시 단원구",
                artistImgURL = "https://cdn.dkilbo.com/news/photo/202103/328795_222527_1909.jpg"
            ),
            ArtistRankData(
                no = 5,
                artistId = "abc005",
                artistName = artistSearchList.get("abc005")?.artistName ?: "",
                artistAddress = "경기 수원시 권선구",
                artistImgURL = "https://news.tbs.seoul.kr/Upload/Image/20221202/00000000000001308345.jpg"
            ),
            ArtistRankData(
                no = 6,
                artistId = "abc006",
                artistName = artistSearchList.get("abc006")?.artistName ?: "",
                artistAddress = "서을 특별시 관악구",
                artistImgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyemqDmEPapQHDeVBwwFuMZeTkp97FvTryJA&usqp=CAU"
            )
        )

        @Composable
        fun getAlarmList(): MutableList<AlarmData> {
            return mutableListOf<AlarmData>(
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

        val paymentInfoList = listOf<PaymentItemInfo>(
            PaymentItemInfo(
                name = "카카오 페이",
                paymentImgURL = "https://play-lh.googleusercontent.com/Ob9Ys8yKMeyKzZvl3cB9JNSTui1lJwjSKD60IVYnlvU2DsahysGENJE-txiRIW9_72Vd"
            ),
            PaymentItemInfo(
                name = "네이버 페이",
                paymentImgURL = "https://mir-s3-cdn-cf.behance.net/project_modules/1400/3393f738210507.575900b317fb4.png"
            ),
        )

        // concert
        var name = "로드 버스킹"
        var url = "https://cdn.pixabay.com/photo/2014/05/21/15/18/musician-349790_640.jpg"


        val gLat = GlobalState.lat;
        val gLon = GlobalState.lon;
        var concertList = mutableMapOf<String, ConcertListInfo>(
            "aaa-000-001" to
                    ConcertListInfo(
                        concertId = "aaa-000-001",
                        imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                        artistName = " 레트로 레벨 (Retro Level)",
                        concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                        location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                        latitude = gLat + 5,
                        longitude = gLon + 5
                    ),
            Pair(
                "aaa-000-002",
                ConcertListInfo(
                    concertId = "aaa-000-002",
                    imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                    artistName = "로보틱 스트라이크 (Robotic Strike)",
                    concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                    location = "서울 서대문구 신촌역로 17 GS 25 앞",
                    latitude = gLat - 5,
                    longitude = gLon + 5
                )
            ),
            Pair(
                "aaa-000-003",
                ConcertListInfo(
                    concertId = "aaa-000-003",
                    imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                    artistName = "화이어파이트 (Fireflight)",
                    concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                    location = "서울 마포구 와우산로21길 19-3 홍익문화공원",
                    latitude = gLat - 5,
                    longitude = gLon - 5
                )
            ),
            Pair(
                "aaa-000-004",
                ConcertListInfo(
                    concertId = "aaa-000-004",
                    imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                    artistName = "루미노브스 (Luminovos)",
                    concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                    location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                ),
            ),

            Pair(
                "aaa-000-005",
                ConcertListInfo(
                    concertId = "aaa-000-005",
                    imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                    artistName = "루미노브스 (Luminovos)",
                    concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                    location = "서울 서대문구 신촌역로 17 GS 25 앞",
                )
            ),
            Pair(
                "aaa-000-006",
                ConcertListInfo(
                    concertId = "aaa-000-006",
                    imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                    artistName = "에코서클 (Echo Circle)",
                    concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                    location = "서울 마포구 와우산로21길 19-3 홍익문화공원",
                )
            ),
            Pair(
                "aaa-000-007",
                ConcertListInfo(
                    concertId = "aaa-000-007",
                    imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                    artistName = "리프리스트 (Refreest)",
                    concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                    location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                )
            ),
            Pair(
                "aaa-000-008",
                ConcertListInfo(
                    concertId = "aaa-000-008",
                    imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                    artistName = "블루스피릿 (Bluespirit)",
                    concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                    location = "서울 서대문구 신촌역로 17 GS 25 앞",
                )
            ),
            Pair(
                "aaa-000-009",
                ConcertListInfo(
                    concertId = "aaa-000-009",
                    imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                    artistName = "크리스탈크래프트 (Crystal Craft)",
                    concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                    location = "서울 마포구 와우산로21길 19-3 홍익문화공원",
                )
            )
        )

        // temp
        val defConcertInfo: ConcertInfo
            get() = ConcertInfo(
                concertId = "aaa-000-001",
                concertTitle = "로드 버스킹",
                concertImgList = listOf(
                    "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151139.jpg",
                    "https://i.ytimg.com/vi/udzpnOi63Jg/maxresdefault.jpg",
                    "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151132.jpg",
                    "https://cdn.skkuw.com/news/photo/201309/10675_5698_2312.jpg",
                    "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/FV575KG7PRPH7BVXOULUYOQ5FA.JPG",
                    "https://www.kns.tv/news/photo/201704/302921_200430_5649.jpg"
                ),
                concertIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                        "\n" +
                        "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                        "\n" +
                        "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                        "\n" +
                        "음악을 사랑한다는 마음 하나라면,\n" +
                        "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                        "\n" +
                        "오늘 여러분과 함께 특별한 추억 쌓아 보기 위해 특별 버스킹을 열게 되었어요. \n" +
                        "\n" +
                        "음악을 사랑하는 분이라면 \n" +
                        "누구나 함께하실 수 있는 즐거운 시간 보내려고 합니다. \n" +
                        "\n" +
                        "무더운 여름 로드 버스킹팀과 함께 \n" +
                        "시원한 시간을 보내보는건 어떨까요?\n" +
                        "\n" +
                        "공연에 대한 자세한 정보는 아래를 참조해주세요!",
                genre = "보컬, 버스킹,",
                startDate = LocalDateTime.of(2023, 7, 21, 13, 0),
                endDate = LocalDateTime.of(2023, 7, 23, 16, 0),
                concertTime = "13:00 ~ 16:00",
                minSupportAccount = 3000,
                cumulativeAudience = 256,
                tags = listOf("로드버스킹", "스트릿", "홍대"),
                memberList = this.memberList,
                locations = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            )

        // 콘서트 아이템
        val concertItems = mutableMapOf<String, ConcertInfo>(
            "aaa-000-001" to ConcertInfo(
                concertId = "aaa-000-001",
                concertTitle = "무슨무슨 버스킹",
                concertImgList = listOf(
                    "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151139.jpg",
                    "https://i.ytimg.com/vi/udzpnOi63Jg/maxresdefault.jpg",
                    "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151132.jpg",
                    "https://cdn.skkuw.com/news/photo/201309/10675_5698_2312.jpg",
                    "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/FV575KG7PRPH7BVXOULUYOQ5FA.JPG",
                    "https://www.kns.tv/news/photo/201704/302921_200430_5649.jpg"
                ),
                concertIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                        "\n" +
                        "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                        "\n" +
                        "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                        "\n" +
                        "음악을 사랑한다는 마음 하나라면,\n" +
                        "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                        "\n" +
                        "오늘 여러분과 함께 특별한 추억 쌓아 보기 위해 특별 버스킹을 열게 되었어요. \n" +
                        "\n" +
                        "음악을 사랑하는 분이라면 \n" +
                        "누구나 함께하실 수 있는 즐거운 시간 보내려고 합니다. \n" +
                        "\n" +
                        "무더운 여름 로드 버스킹팀과 함께 \n" +
                        "시원한 시간을 보내보는건 어떨까요?\n" +
                        "\n" +
                        "공연에 대한 자세한 정보는 아래를 참조해주세요!",
                genre = "보컬, 버스킹,",
                startDate = LocalDateTime.of(2023, 7, 21, 13, 0),
                endDate = LocalDateTime.of(2023, 7, 23, 16, 0),
                concertTime = "13:00 ~ 16:00",
                minSupportAccount = 3000,
                cumulativeAudience = 256,
                tags = listOf("로드버스킹", "스트릿", "홍대"),
                memberList = listOf(
                    ArtistInfo(
                        profileImgURL = "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                        artistName = "블라블라 아티스트",
                        comment = "좋은 음악 함께 공유해요"
                    ),
                    ArtistInfo(
                        profileImgURL = "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                        artistName = "무슨무슨 아티스트",
                        comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
                    )
                ),
                locations = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            )
        )

        val concertLogList = arrayOf<ConcertListInfo>(
            ConcertListInfo(
                concertId = "aaa-000-001",
                imgURL = url,
                artistName = name,
                concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
            ),
            ConcertListInfo(
                concertId = "aaa-000-002",
                imgURL = url,
                artistName = name,
                concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                location = "서울 서대문구 신촌역로 17 GS 25 앞"
            ),
            ConcertListInfo(
                concertId = "aaa-000-003",
                imgURL = url,
                artistName = name,
                concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
            ),
            ConcertListInfo(
                concertId = "aaa-000-004",
                imgURL = url,
                artistName = name,
                concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
            ),
            ConcertListInfo(
                concertId = "aaa-000-005",
                imgURL = url,
                artistName = name,
                concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                location = "서울 서대문구 신촌역로 17 GS 25 앞"
            ),
            ConcertListInfo(
                concertId = "aaa-000-006",
                imgURL = url,
                artistName = name,
                concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
            )
        )

        val ticketInfoList = mutableMapOf<String, TicketInfo>(
            "0000-0000-0001" to
                    TicketInfo(
                        serialNo = "0000-0000-0001",
                        concertName = "홍대 가을 버스킹",
                        teamName = "레트로 레벨 (Retro Level)",
                        concertImgList = listOf(
                            "https://img.hankyung.com/photo/202206/AKR20220622048100051_01_i_P4.jpg",
                            "https://img.khan.co.kr/news/2022/05/13/l_20220513010016444001511313.jpg",
                            "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151132.jpg",
                            "https://blog.kakaocdn.net/dn/lvIrz/btrSqGWAmW3/4D1zPsA9vcYOCroUHfKkM0/img.png"
                        ),
                        dateTime = LocalDateTime.now(),
                        location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                        isOnAir = true
                    ),

            Pair(
                "0000-0000-0002",
                TicketInfo(
                    serialNo = "0000-0000-0002",
                    concertName = "로보틱 정기 버스킹",
                    teamName = "로보틱 스트라이크 (Robotic Strike)",
                    dateTime = LocalDateTime.now(),
                    location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                )
            ),
            Pair(
                "0000-0000-0003",
                TicketInfo(
                    serialNo = "0000-0000-0003",
                    concertName = "신나는 화이어 콘서트",
                    teamName = "화이어파이트 (Fireflight)",
                    dateTime = LocalDateTime.now(),
                    location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                )
            ),
            Pair(
                "0000-0000-0004",
                TicketInfo(
                    serialNo = "0000-0000-0004",
                    concertName = "루미너스 스페이스",
                    teamName = "루미노브스 (Luminovos)",
                    dateTime = LocalDateTime.now(),
                    location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                )
            )
        )

        // member
        val crewList = listOf<ArtistInfo>(
            ArtistInfo(
                userId = "tired_cat",
                profileImgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                email = "tired_cat@naver.com"
            ),
            ArtistInfo(
                userId = "swag_cat",
                profileImgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                email = "swag_cat@gmail.com"
            ),
            ArtistInfo(
                userId = "sad_cat",
                profileImgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                email = "sad_cat@daum.net"
            )
        )

        // 기본 무한 스크롤 배너 이미지 리스트
        val defImgList = listOf(
            "https://thumbs.dreamstime.com/b/login-banner-template-ribbon-label-sign-177646419.jpg",
            "https://blog.kakaocdn.net/dn/HUGVj/btrJloRg451/mctRUnHYAgTKvocX1HxXiK/img.jpg",
            "https://as1.ftcdn.net/v2/jpg/04/86/66/48/1000_F_486664896_TxOuOR9WcKdvle5uG4kCZVnL80QyWp1t.jpg",
            "https://img.freepik.com/free-vector/best-sale-abstract-horizontal-banner-design_1017-31300.jpg",
            "https://png.pngtree.com/png-vector/20220530/ourmid/pngtree-photo-camera-horizontal-banner-png-image_4762429.png"
        )
    }

}