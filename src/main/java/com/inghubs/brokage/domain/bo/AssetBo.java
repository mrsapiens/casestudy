package com.inghubs.brokage.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssetBo {

    private CustomerBo customerBo;
    private String assetName;
    private Integer usableSize;
    private Integer size;
}
