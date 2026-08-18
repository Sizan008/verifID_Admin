package com.leads.microcube.verifidadmin.assistedekyc;

import com.leads.microcube.verifidadmin.assistedekyc.query.AssistedEkycResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssistedEkycQueryServiceImpl implements AssistedEkycQueryService {

  private final AssistedEkycSettings assistedEkycSettings;

  @Override
  public AssistedEkycResponse retrieveIndex() {
    return AssistedEkycResponse.builder()
        .url(assistedEkycSettings.getUrl())
        .build();
  }
}
