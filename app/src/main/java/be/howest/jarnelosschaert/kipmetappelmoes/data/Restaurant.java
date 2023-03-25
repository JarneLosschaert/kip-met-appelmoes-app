package be.howest.jarnelosschaert.kipmetappelmoes.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Restaurant {
    private final int id;
    private final String name;
    private final String address;
    private final String city;
    private final String postalCode;

    private final float longitude;
    private final float latitude;
    private final String phone;
    private final String email;
    private final String website;
    private final Map<String, String> openingHours;
    private final String description;
    private final List<String> images;
    private final Set<ChildFriendliness> childFriendliness;
    private final Set<EatingOptions> eatingOptions;
    private final List<Review> reviews;

    public Restaurant(int id, String name, String address, String city, String postalCode, float longitude, float latitude, String phone, String email, String website, Map<String, String> openingHours, String description, List<String> images, Set<ChildFriendliness> childFriendliness, Set<EatingOptions> eatingOptions, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.openingHours = openingHours;
        this.description = description;
        this.images = images;
        this.childFriendliness = childFriendliness;
        this.eatingOptions = eatingOptions;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public Map<String, String> getOpeningHours() {
        return openingHours;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

    public Set<ChildFriendliness> getChildFriendliness() {
        return childFriendliness;
    }

    public Set<EatingOptions> getEatingOptions() {
        return eatingOptions;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
