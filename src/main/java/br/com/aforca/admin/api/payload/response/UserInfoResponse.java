package br.com.aforca.admin.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserInfoResponse {
  private Long id;
  private String email;
  private String senha;
}
