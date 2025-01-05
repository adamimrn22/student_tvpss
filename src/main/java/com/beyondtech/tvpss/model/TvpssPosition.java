package com.beyondtech.tvpss.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tvpss_position")
public class TvpssPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // The position name (e.g., Principal, Teacher)

    @Column(name = "school_code")  // Foreign key to the school table
    private String schoolCode;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }
}
