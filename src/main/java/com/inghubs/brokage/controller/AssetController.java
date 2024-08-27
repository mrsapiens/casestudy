package com.inghubs.brokage.controller;

import com.inghubs.brokage.domain.bo.AssetBo;
import com.inghubs.brokage.domain.mapper.AssetMapper;
import com.inghubs.brokage.domain.model.Asset;
import com.inghubs.brokage.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/asset")
public class AssetController {

    private final AssetService assetService;
    private final AssetMapper assetMapper;

    @PostMapping("/create")
    public ResponseEntity<Asset> create(@Valid @RequestBody Asset assetRequest) {
        AssetBo assetBo = assetService.createAsset(assetMapper.convert(assetRequest));

        return ResponseEntity.ok(assetMapper.convert(assetBo));
    }

}
