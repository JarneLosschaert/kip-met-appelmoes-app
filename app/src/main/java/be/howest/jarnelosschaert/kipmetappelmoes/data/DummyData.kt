package be.howest.jarnelosschaert.kipmetappelmoes.data

import be.howest.jarnelosschaert.kipmetappelmoes.data.models.*
import java.time.LocalDate

object DummyData {
    private val jari = User(1, "Jari", "Aap", "jarivalentine@gmail.com", "1234567890", listOf())
    private val jb = User(2, "Jan", "Bap", "jarivalentine@gmail.com", "1234567890", listOf())
    private val timmy = User(3, "Timmy", "Waal", "jarivalentine@gmail.com", "1234567890", listOf())
    private val arthur = User(4, "Arthur", "wit", "jarivalentine@gmail.com", "1234567890", listOf())

    private val review1 = Review(1, jari, "Goede bediening!", 5, LocalDate.of(2020, 1, 1))
    private val review2 = Review(2, jb, "De kroketten waren op...", 1, LocalDate.of(2022, 11, 23))
    private val review3 = Review(3, timmy, "Lekker eten hoor, maar niet zo gezellig", 3, LocalDate.of(2022, 10, 20))
    private val review4 = Review(4, arthur, "Ik zal hier zeker meer komen!", 4, LocalDate.of(2023, 3, 20))

    var restaurants = listOf(
        Restaurant(
            1,
            "Den Huzaar",
            "Vlamingstraat 36",
            "Brugge",
            "8000",
            51.210327,
            3.2248914,
            "050333797",
            "info@denhuzaar.be",
            "https://www.denhuzaar.be/",
            mapOf(
                "Maandag" to "11:30 - 22:30",
                "Dinsdag" to "11:30 - 22:30",
                "Woensdag" to "11:30 - 22:30",
                "Donderdag" to "11:30 - 22:30",
                "Vrijdag" to "11:30 - 22:30",
                "Zaterdag" to "11:30 - 22:30",
                "Zondag" to "11:30 - 22:30"
            ),
            "Geniet u graag van een romantisch en gezellig diner? Wij bedienen u graag in ons charmant restaurant in het hartje van Brugge.",
            listOf(
                "https://static.wixstatic.com/media/a0e0ce_f89cacd6eec94a45b83e8f9403d4f97d~mv2.jpg/v1/fit/w_2500,h_1330,al_c/a0e0ce_f89cacd6eec94a45b83e8f9403d4f97d~mv2.jpg",
                "https://res.cloudinary.com/tf-lab/image/upload/w_600,h_337,c_fill,q_auto,f_auto/restaurant/5483e436-bc28-4077-aaec-7b0353c1a369/rejected/45d0438a-5ef0-452b-bf89-83f557878b86.jpg",
                "https://img.grouponcdn.com/deal/ewFPD9Yqjd62SKgTKWzy827ptzT/ew-700x420/v1/c870x524.jpg"
            ),
            setOf(ChildFriendliness.PARKING, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE),
            setOf(EatingOption.LUNCH, EatingOption.DINNER, EatingOption.DRINKS, EatingOption.BREAKFAST),
            listOf(
                review1,
                review2,
                review3
            )
        ),
        Restaurant(
            2,
            "The Olive Tree",
            "Wollestraat 3",
            "Brugge",
            "8000",
            51.20832,
            3.2253008,
            "050330081",
            "info@theolivetree.be",
            "http://www.theolivetree-brugge.com/",
            mapOf(
                "Maandag" to "18:00 – 22:00",
                "Dinsdag" to "Gesloten",
                "Woensdag" to "18:00 – 22:00",
                "Donderdag" to "18:00 - 22:00",
                "Vrijdag" to "18:00 - 22:00",
                "Zaterdag" to "18:00 - 22:00",
                "Zondag" to "18:00 - 22:00"
            ),
            "Nikos, Aspa en familie heten u van harte welkom in hun OLIVE TREE Restaurant met Grieks-mediterrane smaken.\n" +
                    "Het OLIVE TREE Restaurant brengt de warme traditie van Griekse gastvrijheid samen met heerlijke Grieks-mediterrane keuken, geüpdatet met een eigentijdse twist. U vindt de combinatie van smaak en aroma, de knapperige smaak van traditionele smaakmakers en de meest verse ingrediënten uit het land en de zee. Voel de warmte van de mensen in hun gastvrije glimlach en de charme van de Middellandse Zee in haar hoge energie en levendige geest.",
            listOf(
                "https://www.theolivetree-brugge.com/images/restaur1.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/0d/34/6d/41/20161005-184735-largejpg.jpg",
                "https://www.theolivetree-brugge.com/images/footer/larges/4.jpg"
            ),
            setOf(ChildFriendliness.PLAYGROUND_INSIDE, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.PLAYGROUND_OUTSIDE, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE),
            setOf(EatingOption.DINNER, EatingOption.LUNCH, EatingOption.BREAKFAST, EatingOption.TAKEOUT),
            listOf(
                review4,
                review2,
                review1
            )
        ),
        Restaurant(
            3,
            "Arthie's",
            "Wollestraat 10",
            "Brugge",
            "8000",
            51.207703,
            3.2258651,
            "050334313",
            "info@arthies.be",
            "http://arthies.be/",
            mapOf(
                "Maandag" to "11:00 – 22:00",
                "Dinsdag" to "Gesloten",
                "Woensdag" to "11:00 – 22:00",
                "Donderdag" to "11:00 – 22:00",
                "Vrijdag" to "11:00 – 22:00",
                "Zaterdag" to "11:00 – 22:00",
                "Zondag" to "11:00 – 22:00"
            ),
            "Arthie's van Andries  is een familie horecabedrijf sinds 1996 die een nieuwe zaak geopend heeft in 2017. Onze zonen Sam en Pieter volgden beiden  koksopleiding in hotelschool spermalie te Brugge en nu bieden zij een speelse kijk op de Belgische keuken met brede wereldse invloeden met de nadruk op lokale, rustieke, seizoensgebonden en duurzame ingrediënten. De gerechten zijn ontworpen om te proeven en te delen en worden aangevuld met een heerlijke cocktailervaring die wordt geserveerd in een levendige sfeer die gasten verwelkomen.",
            listOf(
                "https://www.afhaalgerechten.be/wp-content/uploads/job-manager-uploads/main_image/2020/11/tafel-2.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/0b/6a/ed/46/arthie-s.jpg",
                "https://images.socialdeal.nl/bedrijf/arthies-22111710164396.jpg"
            ),
            setOf(ChildFriendliness.CHILD_MENU, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.PLAYGROUND_OUTSIDE, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE),
            setOf(EatingOption.DINNER, EatingOption.SNACKS, EatingOption.LUNCH),
            listOf(
                review3,
                review4,
                review1
            )
        ),
        Restaurant(
            4,
            "De Stove",
            "Kleine Sint-Amandsstraat 4",
            "Brugge",
            "8000",
            51.208336,
            3.2226572,
            "050337835",
            "info@destove.be",
            "http://www.restaurantdestove.be/",
            mapOf(
                "Maandag" to "11:45 - 21:00",
                "Dinsdag" to "11:45 - 21:00",
                "Woensdag" to "Gesloten",
                "Donderdag" to "Gesloten",
                "Vrijdag" to "11:45 - 21:00",
                "Zaterdag" to "11:45 - 21:00",
                "Zondag" to "11:45 - 21:00"
            ),
            "Restaurant De Stove is een klein en sfeervol restaurant in het gekende Sint-Amandsstraatje in het centrum van Brugge, op wandelafstand van de Grote Markt en ’t Zand. Je kan er gezellig en heerlijk lunchen of uitgebreid dineren dichtbij de authentieke kachel, die vandaag de dag nog steeds de charme en authenticiteit van het restaurant en de stad weerspiegelt. Debbie staat vol passie achter het fornuis, terwijl Nico je met veel warmte bedient aan de tafel. We verwelkomen je graag!",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/0d/81/f1/a3/cosy-huiskamer-restaurant.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/2c/95/15/the-big-clock.jpg",
                "https://media-cdn.tripadvisor.com/media/photo-s/01/9f/31/b0/de-stove.jpg"
            ),
            setOf(ChildFriendliness.CHILD_MENU, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE, ChildFriendliness.PARKING),
            setOf(EatingOption.DINNER, EatingOption.SNACKS),
            listOf(
                review2,
                review4,
                review1
            )
        ),
        Restaurant(
            5,
            "Den Amand",
            "Sint-Amandsstraat 4",
            "Brugge",
            "8000",
            51.20861,
            3.2233825,
            "050340122",
            "info@denamand.be",
            "http://www.denamand.be/",
            mapOf(
                "Maandag" to "Gesloten",
                "Dinsdag" to "12:00 – 21:00",
                "Woensdag" to "12:00 – 21:00",
                "Donderdag" to "12:00 – 21:00",
                "Vrijdag" to "12:00 – 21:00",
                "Zaterdag" to "12:00 – 21:00",
                "Zondag" to "12:00 – 21:00"
            ),
            "In Bistro Den Amand, in het historische centrum van Brugge kun je heerlijk tafelen. Verse gerechten en een een (h)eerlijke keuken zijn onze troef. Herontdek de eenvoudige genoegens van een traditionele maaltijd. Chef-kok An en gastheer Arnout verwennen u bij elke gang.",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/03/91/8b/5b/den-amand.jpg",
                "https://www.denamand.be/sites/denamand.dd/files/styles/slider/public/sliders/denamand2-223.jpg?itok=fk-HfJD2",
                "https://lh3.googleusercontent.com/p/AF1QipMNv69LzZ4qE_y82oKh87E2iz27gdZ5C_JObE1x=s1600-w640"
            ),
            setOf(ChildFriendliness.PLAYGROUND_INSIDE, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE, ChildFriendliness.PARKING, ChildFriendliness.PLAYGROUND_OUTSIDE),
            setOf(EatingOption.DINNER, EatingOption.BREAKFAST),
            listOf(
                review3,
                review4,
                review1
            )
        ),
        Restaurant(
            5,
            "Brugeoise",
            "Markt 27",
            "Brugge",
            "8000",
            51.209236,
            3.2238834,
            "050332132",
            "info@brugeoise.be",
            "http://www.brugeoise.be/",
            mapOf(
                "Maandag" to "10:00 – 23:00",
                "Dinsdag" to "10:00 – 23:00",
                "Woensdag" to "10:00 – 23:00",
                "Donderdag" to "10:00 – 23:00",
                "Vrijdag" to "10:00 – 23:00",
                "Zaterdag" to "10:00 – 23:00",
                "Zondag" to "10:00 – 23:00"
            ),
            "Ons team staat klaar om jullie een culinaire, leuke ervaring te laten beleven. We raden dan ook aan om ons Experience Menu te proberen.",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/04/53/ab/86/bistro-brugeoise.jpg",
                "https://www.visitbrugesconventionbureau.be/images/photolib/12574_itla.jpg",
                "https://www.watzijzegt.com/wp-content/uploads/2012/12/brugge-grote-markt.jpg"
            ),
            setOf(ChildFriendliness.CHILD_MENU, ChildFriendliness.HIGHCHAIRS, ChildFriendliness.CHANGING_TABLE, ChildFriendliness.MICROWAVE, ChildFriendliness.PARKING, ChildFriendliness.PLAYGROUND_OUTSIDE),
            setOf(EatingOption.DINNER, EatingOption.SNACKS, EatingOption.SNACKS),
            listOf(
                review2,
                review1,
                review4
            )
        )
    )

    var me = User(5, "Jarne", "Losschaert", "jarnelosschaert@gmail.com", "1234567890", listOf(
        restaurants[0], restaurants[1], restaurants[2]))
}