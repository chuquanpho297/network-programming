package com.networking.meetingclient.protocol.response;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@ToString(callSuper = true)
public class PagingResponse<T> extends OkResponse {
    private List<T> lists;
    private Metadata metadata;
}

@Setter
@NoArgsConstructor
@ToString
class Metadata {
    private Integer currentPage;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private Integer totalPage;
    private Integer totalRow;
}