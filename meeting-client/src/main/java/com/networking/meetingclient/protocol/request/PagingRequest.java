package com.networking.meetingclient.protocol.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
public class PagingRequest extends Request {
    private Integer page;
    private Integer size;

    public PagingRequest(String action, Integer page, Integer size) {
        super(action);
        this.page = page != null ? page : 1;
        this.size = size != null ? size : 20;
    }
}
