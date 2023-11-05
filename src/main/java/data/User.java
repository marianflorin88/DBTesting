package data;

public class User {
    private long id;
    private String name;
    private String job;
    private String email;
    private String avatar;
    private String location;

    public User(long id, String name, String job, String email, String avatar, String location) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.email = email;
        this.avatar = avatar;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getLocation() {
        return location;
    }
}
