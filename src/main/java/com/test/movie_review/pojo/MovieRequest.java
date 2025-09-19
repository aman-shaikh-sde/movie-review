package com.test.movie_review.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class MovieRequest {


    @NotBlank(message = "Cant be Blank")
    private String tittle;
    @NotBlank(message = "Cant be Blank")
    private String description;
    private Date releaseDate;
    @NotBlank(message="Cant be Blank")
    private String genre;
}
