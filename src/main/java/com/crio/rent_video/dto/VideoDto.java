package com.crio.rent_video.dto;

import com.crio.rent_video.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private Long id;
    private String title;
    private String director;
    private Genre genre;
    private Boolean status;
}
