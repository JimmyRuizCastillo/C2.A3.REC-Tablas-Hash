public class Business {
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;

    public Business(String id, String name, String address, String city, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public String getId() {
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

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "City: " + city + "\n" +
                "State: " + state;
    }
}