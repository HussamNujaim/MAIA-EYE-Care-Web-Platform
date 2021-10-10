package com.maiacare.serverside.Utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageCondition implements Serializable {

    private Integer pageSize;

    private Integer currentPage;


}
