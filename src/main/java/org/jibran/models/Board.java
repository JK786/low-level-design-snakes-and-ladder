package org.jibran.models;

import lombok.*;

import java.util.List;


@RequiredArgsConstructor
@Data
public class Board {

    //Non null fields are required to be initialized in the constructor
    @NonNull
    private  List<Snake> snakes;

    //Non null fields are required to be initialized in the constructor
    @NonNull
    private  List<Ladder> ladders;


    private  int[] grid = new int[100];



}
