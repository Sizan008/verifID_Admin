package com.leads.microcube.verifidadmin.channel;

import com.leads.microcube.verifidadmin.channel.command.CreateChannel;
import com.leads.microcube.verifidadmin.channel.command.UpdateChannel;
import com.leads.microcube.verifidadmin.channel.query.ChannelDetails;
import com.leads.microcube.verifidadmin.channel.query.ChannelResponse;
import com.leads.microcube.verifidadmin.channel.query.ChannelSummaryResponse;
import com.leads.microcube.verifidadmin.common.response.ApiResponse;
import com.leads.microcube.verifidadmin.common.security.PermissionType;
import com.leads.microcube.verifidadmin.common.security.RequirePermission;
import com.leads.microcube.verifidadmin.log.UserActivityLogService;
import com.leads.microcube.verifidadmin.log.command.RecordCurrentUserActivity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Channel")
@RequiredArgsConstructor
@Tag(name="Channel")
public class ChannelController {

  private static final URI INDEX_URI = URI.create("/api/Channel/Index");

  private final ChannelService channelService;
  private final ChannelQueryService channelQueryService;
  private final UserActivityLogService userActivityLogService;

  @GetMapping("/Index")
  @RequirePermission(targetPath = "Channel/Index")
  public ResponseEntity<ApiResponse<List<ChannelSummaryResponse>>> retrieveChannels() {
    List<ChannelSummaryResponse> channels = channelQueryService.retrieveChannels();
    return ResponseEntity.ok(ApiResponse.success(channels));
  }

  @GetMapping("/Details")
  @RequirePermission(targetPath = "Channel/Index")
  public ResponseEntity<ApiResponse<ChannelResponse>> retrieveChannel(
      @RequestParam("id") Integer id) {
    ChannelResponse channel =
        channelQueryService.retrieveChannel(new ChannelDetails(id));
    return ResponseEntity.ok(ApiResponse.success(channel));
  }

  @GetMapping("/Create")
  @RequirePermission(targetPath = "Channel/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<CreateChannel>> retrieveCreateForm() {
    return ResponseEntity.ok(ApiResponse.success(new CreateChannel()));
  }

  @PostMapping("/Create")
  @RequirePermission(targetPath = "Channel/Index", value = PermissionType.ADD)
  public ResponseEntity<ApiResponse<Void>> registerChannel(
      @Valid @RequestBody CreateChannel command) {
    channelService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Create")
            .actionParticulars("is Creating New Channel")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Channel created successfully.", null));
  }

  @GetMapping("/Edit/{id}")
  @RequirePermission(targetPath = "Channel/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ChannelResponse>> retrieveChannelForEdit(
      @PathVariable("id") Integer id) {
    ChannelResponse channel =
        channelQueryService.retrieveChannel(new ChannelDetails(id));
    return ResponseEntity.ok(ApiResponse.success(channel));
  }

  @GetMapping(value = "/Edit", params = "id")
  @RequirePermission(targetPath = "Channel/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<ChannelResponse>> retrieveChannelForEditByQuery(
      @RequestParam("id") Integer id) {
    return retrieveChannelForEdit(id);
  }

  @PostMapping("/Edit")
  @RequirePermission(targetPath = "Channel/Index", value = PermissionType.EDIT)
  public ResponseEntity<ApiResponse<Void>> updateChannel(
      @Valid @RequestBody UpdateChannel command) {
    channelService.process(command);
    userActivityLogService.process(
        RecordCurrentUserActivity.builder()
            .trackingNo(0L)
            .stepId(0)
            .actionType("Edit")
            .actionParticulars("is Editing Channel")
            .requestChannel("")
            .build());
    return ResponseEntity.ok(ApiResponse.success("Channel updated successfully.", null));
  }

  @GetMapping("/Delete/{id}")
  public ResponseEntity<Void> retrieveDelete(@PathVariable("id") Integer id) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @PostMapping("/Delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    return ResponseEntity.status(HttpStatus.FOUND).location(INDEX_URI).build();
  }
}
