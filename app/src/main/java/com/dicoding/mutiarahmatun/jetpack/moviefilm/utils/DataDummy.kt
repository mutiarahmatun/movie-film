package com.dicoding.mutiarahmatun.jetpack.moviefilm.utils

import com.dicoding.mutiarahmatun.jetpack.moviefilm.R
import com.dicoding.mutiarahmatun.jetpack.moviefilm.data.FilmEntity

object DataDummy {

    fun generateDummyMovies(): ArrayList<FilmEntity> {
        val movies = ArrayList<FilmEntity>()

        movies.add(
            FilmEntity(
                "MOVIE_1",
                "Alita: Battle Angle",
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa.",
                "Aksi, Cerita Fiksi, Petualangan",
                "2019",
                    R.drawable.poster_alita,
                    R.drawable.latar_alita)
        )

        movies.add(
            FilmEntity(
                "MOVIE_2",
                "Aquaman",
                "Dulunya rumah bagi peradaban paling maju di Bumi, Atlantis sekarang menjadi kerajaan bawah air yang diperintah oleh Raja Orm yang haus kekuasaan. Dengan pasukan besar yang dimilikinya, Orm berencana untuk menaklukkan orang-orang samudra yang tersisa dan kemudian dunia permukaan. Yang menghalangi jalannya adalah Arthur Curry, saudara setengah manusia Orm, saudara setengah Atlantis dan pewaris sejati takhta.",
                " Aksi, Petualangan, Fantasi",
                "2018",
                    R.drawable.poster_aquaman,
                    R.drawable.latar_aquaman)
        )

        movies.add(
            FilmEntity(
                "MOVIE_3",
                "A Star Is Born",
                "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
                "Drama, Percintaan, Musik",
                "2018",
                    R.drawable.poster_a_start_is_born,
                    R.drawable.latar_a_star)
        )

        movies.add(
            FilmEntity(
            "MOVIE_4",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "Drama, Musik",
            "2018",
                    R.drawable.poster_bohemian,
                    R.drawable.latar_bohemian)
        )
        movies.add(
            FilmEntity(
            "MOVIE_5",
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "Aksi, Kejahatan, Cerita Seru",
            "2019",
                    R.drawable.poster_cold_persuit,
                    R.drawable.latar_cold)
        )
        movies.add(
            FilmEntity(
            "MOVIE_6",
            "How to Train Your Dragon: The Hidden World",
            "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
            "Animasi, Keluarga, Petualangan",
            "2019",
                    R.drawable.poster_how_to_train,
                    R.drawable.latar_how_to_train)
        )
        movies.add(
            FilmEntity(
            "MOVIE_7",
            "Avengers: Infinity War",
            "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi.",
            "Petualangan, Aksi, Cerita Seru",
            "2018",
                    R.drawable.poster_infinity_war,
                    R.drawable.latar_infinity)
        )
        movies.add(
            FilmEntity(
            "MOVIE_8",
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "Keluarga, Animasi, Komedi, Petualangan",
            "2018",
                    R.drawable.poster_ralph,
                    R.drawable.latar_ralph)
        )
        movies.add(
            FilmEntity(
            "MOVIE_9",
            "Robin Hood",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            "Petualangan, Aksi, Cerita Seru",
            "2018",
                    R.drawable.poster_robin_hood,
                    R.drawable.latar_robin)
        )
        movies.add(
            FilmEntity(
            "MOVIE_10",
            "Spider-Main: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            "Aksi, Petualangan, Animasi",
            "2018",
                    R.drawable.poster_spiderman,
                    R.drawable.latar_spider)
        )
        return movies
    }

    fun generateDummyTvShows(): ArrayList<FilmEntity> {
        val tvShows = ArrayList<FilmEntity>()

        tvShows.add(
            FilmEntity(
            "TVSHOW_1",
            "The Arrow",
            "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
            "Kejahatan, Drama, Misteri",
            "2012",
                    R.drawable.poster_arrow,
                    R.drawable.latar_arrow)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_2",
            "Dragon Ball Absalon",
            "The series begins twelve years after Goku is seen leaving on Shenron not at the end of Dragon Ball GT, and diverges entirely into its own plot from there, on an alternate timeline from the one which shows Goku Jr. fighting Vegeta Jr. at the World Martial Arts Tournament. In this series, Majuub has reached new levels of power, and has honed the techniques taught to him by Goku. We also see Gotenks finally mature, and Vegeta more powerful than ever.Each character will bring forth their own set of capabilities and purpose, winning fights and being relevant, in order to create an environment that emphasizes teamwork as well as individual worth for each character. In this series, the main set of villains are Saiyans, with abilities highly similar to the Saiyans that we are familiar with. These new Saiyans are more powerful than any threat the Z Fighters have ever faced, but after twelve years of intensive training, our heroes will not be easily defeated .",
            "Sci-fi & Fantasy, Aksi & Petualangan",
            "2012",
                    R.drawable.poster_dragon_ball,
                    R.drawable.latar_dragon)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_3",
            "Family Guy",
            "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ).",
            "Animasi, Komedi",
            "1999",
                    R.drawable.poster_family_guy,
                    R.drawable.latar_family)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_4",
            "The Flash",
            "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
            "Drama, Sci-fi & Fantasy",
            "2014",
                    R.drawable.poster_flash,
                    R.drawable.latar_flash)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_5",
            "Gotham",
            "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka.",
            "Drama, Fantasi, Kejahatan",
            "2014",
                    R.drawable.poster_gotham,
                    R.drawable.latar_gotham)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_6",
            "Grey's Anatomy",
            "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
            "Drama",
            "2005",
                    R.drawable.poster_grey_anatomy,
                    R.drawable.latar_grey)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_7",
            "Hanna",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "Aksi & Petualangan, Drama",
            "2019",
                    R.drawable.poster_hanna,
                    R.drawable.latar_hanna)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_8",
            "Naruto Shippuden",
            "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.",
            "Animasi, Komedi, Drama",
            "2007",
                    R.drawable.poster_naruto_shipudden,
                    R.drawable.latar_naruto)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_9",
            "NCIS",
            "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
            "Aksi & Petualangan, Kejahatan, Drama",
            "2003",
                    R.drawable.poster_ncis,
                    R.drawable.latar_ncis)
        )
        tvShows.add(
            FilmEntity(
            "TVSHOW_10",
            "The Simpsons",
            "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
            "Animasi, Komedi, Keluarga, Drama",
            "1989",
                    R.drawable.poster_the_simpson,
                    R.drawable.latar_simpsons)
        )

        return tvShows
    }

}