package be.howest.jarnelosschaert.kipmetappelmoes.data

import java.time.LocalDate
import java.util.*

object DataSource {
    val user = User(1, "John", "Doe", "JohnDoe@gmail.com", "1234567890")
    val restaurants = listOf(
        Restaurant(
            1,
            "The Little Spoon",
            "123 Main Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-1234",
            "info@greenspoon.com",
            "http://www.greenspoon.com",
            mapOf(
                "Monday" to "9am - 10pm",
                "Tuesday" to "9am - 10pm",
                "Wednesday" to "9am - 10pm",
                "Thursday" to "9am - 10pm",
                "Friday" to "9am - 11pm",
                "Saturday" to "8am - 11pm",
                "Sunday" to "8am - 9pm"
            ),
            "The Green Spoon is a farm-to-table restaurant that specializes in locally sourced, organic ingredients.",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/1a/18/3a/cb/restaurant-le-47.jpg",
                "https://res.cloudinary.com/tf-lab/image/upload/f_auto,q_auto,w_1350,h_759/restaurant/e74f0fd3-4870-4186-bade-cde50062e2ab/732e9bcf-52fa-4f3c-bb07-08b3657484f6.jpg",
                "https://www.saporo.be/nl/thumbnail/full/rLjM8/CONTACT.jpg"
            ),
            setOf(ChildFriendliness.PARKING, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.BREAKFAST, EatingOptions.LUNCH),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(2023, 3, 15)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2022, 9, 1)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2021, 5, 27))
            )
        ),
        Restaurant(
            2,
            "Taco Town",
            "456 Elm Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-5678",
            "info@tacotown.com",
            "http://www.tacotown.com",
            mapOf(
                "Monday" to "11am - 9pm",
                "Tuesday" to "11am - 9pm",
                "Wednesday" to "11am - 9pm",
                "Thursday" to "11am - 9pm",
                "Friday" to "11am - 10pm",
                "Saturday" to "10am - 10pm",
                "Sunday" to "10am - 8pm"
            ),
            "Taco Town serves delicious tacos and other Mexican favorites.",
            listOf(
                "https://storage.kempinski.com/cdn-cgi/image/w=1920,f=auto,g=auto,fit=scale-down/ki-cms-prod/images/1/9/8/6/86891-1-eng-GB/5b43edfee48c-73660559_4K.jpg",
                "https://www.tacotown.com/images/taco2.jpg",
                "https://www.tacotown.com/images/taco3.jpg"
            ),
            setOf(ChildFriendliness.PLAYGROUND_INSIDE, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.DRINKS, EatingOptions.SNACKS),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(1995, 6, 10)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2008, 12, 31)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2015, 9, 23))
            )
        ),
        Restaurant(
            1,
            "The Little Spoon",
            "123 Main Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-1234",
            "info@greenspoon.com",
            "http://www.greenspoon.com",
            mapOf(
                "Monday" to "9am - 10pm",
                "Tuesday" to "9am - 10pm",
                "Wednesday" to "9am - 10pm",
                "Thursday" to "9am - 10pm",
                "Friday" to "9am - 11pm",
                "Saturday" to "8am - 11pm",
                "Sunday" to "8am - 9pm"
            ),
            "The Green Spoon is a farm-to-table restaurant that specializes in locally sourced, organic ingredients.",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/1a/18/3a/cb/restaurant-le-47.jpg",
                "https://res.cloudinary.com/tf-lab/image/upload/f_auto,q_auto,w_1350,h_759/restaurant/e74f0fd3-4870-4186-bade-cde50062e2ab/732e9bcf-52fa-4f3c-bb07-08b3657484f6.jpg",
                "https://www.saporo.be/nl/thumbnail/full/rLjM8/CONTACT.jpg"
            ),
            setOf(ChildFriendliness.PARKING, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.BREAKFAST, EatingOptions.LUNCH),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(2023, 3, 15)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2022, 9, 1)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2021, 5, 27))
            )
        ),
        Restaurant(
            2,
            "Taco Town",
            "456 Elm Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-5678",
            "info@tacotown.com",
            "http://www.tacotown.com",
            mapOf(
                "Monday" to "11am - 9pm",
                "Tuesday" to "11am - 9pm",
                "Wednesday" to "11am - 9pm",
                "Thursday" to "11am - 9pm",
                "Friday" to "11am - 10pm",
                "Saturday" to "10am - 10pm",
                "Sunday" to "10am - 8pm"
            ),
            "Taco Town serves delicious tacos and other Mexican favorites.",
            listOf(
                "https://storage.kempinski.com/cdn-cgi/image/w=1920,f=auto,g=auto,fit=scale-down/ki-cms-prod/images/1/9/8/6/86891-1-eng-GB/5b43edfee48c-73660559_4K.jpg",
                "https://www.tacotown.com/images/taco2.jpg",
                "https://www.tacotown.com/images/taco3.jpg"
            ),
            setOf(ChildFriendliness.PLAYGROUND_OUTSIDE, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.DRINKS, EatingOptions.SNACKS),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(1995, 6, 10)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2008, 12, 31)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2015, 9, 23))
            )
        ),
        Restaurant(
            1,
            "The Green Spoon",
            "123 Main Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-1234",
            "info@greenspoon.com",
            "http://www.greenspoon.com",
            mapOf(
                "Monday" to "9am - 10pm",
                "Tuesday" to "9am - 10pm",
                "Wednesday" to "9am - 10pm",
                "Thursday" to "9am - 10pm",
                "Friday" to "9am - 11pm",
                "Saturday" to "8am - 11pm",
                "Sunday" to "8am - 9pm"
            ),
            "The Green Spoon is a farm-to-table restaurant that specializes in locally sourced, organic ingredients.",
            listOf(
                "https://media-cdn.tripadvisor.com/media/photo-s/1a/18/3a/cb/restaurant-le-47.jpg",
                "https://res.cloudinary.com/tf-lab/image/upload/f_auto,q_auto,w_1350,h_759/restaurant/e74f0fd3-4870-4186-bade-cde50062e2ab/732e9bcf-52fa-4f3c-bb07-08b3657484f6.jpg",
                "https://www.saporo.be/nl/thumbnail/full/rLjM8/CONTACT.jpg"
            ),
            setOf(ChildFriendliness.PARKING, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.BREAKFAST, EatingOptions.LUNCH),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(2023, 3, 15)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2022, 9, 1)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2021, 5, 27))
            )
        ),
        Restaurant(
            2,
            "Taco Town",
            "456 Elm Street",
            "Anytown",
            "12345",
            97.34F,
            544F,
            "555-5678",
            "info@tacotown.com",
            "http://www.tacotown.com",
            mapOf(
                "Monday" to "11am - 9pm",
                "Tuesday" to "11am - 9pm",
                "Wednesday" to "11am - 9pm",
                "Thursday" to "11am - 9pm",
                "Friday" to "11am - 10pm",
                "Saturday" to "10am - 10pm",
                "Sunday" to "10am - 8pm"
            ),
            "Taco Town serves delicious tacos and other Mexican favorites.",
            listOf(
                "https://storage.kempinski.com/cdn-cgi/image/w=1920,f=auto,g=auto,fit=scale-down/ki-cms-prod/images/1/9/8/6/86891-1-eng-GB/5b43edfee48c-73660559_4K.jpg",
                "https://www.tacotown.com/images/taco2.jpg",
                "https://www.tacotown.com/images/taco3.jpg"
            ),
            setOf(ChildFriendliness.PLAYGROUND_INSIDE, ChildFriendliness.HIGHCHAIRS),
            setOf(EatingOptions.DRINKS, EatingOptions.SNACKS),
            listOf(
                Review(1, user, "Great food and service!", 4, LocalDate.of(1995, 6, 10)),
                Review(1, user, "Very slow service!", 2, LocalDate.of(2008, 12, 31)),
                Review(1, user, "Pretty good!", 3, LocalDate.of(2015, 9, 23))
            )
        )
    )

}