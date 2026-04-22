package inf.frohlich.projapi.mapper;

import inf.frohlich.projapi.dto.TelefoneDTORequest;
import inf.frohlich.projapi.dto.TelefoneDTOResponse;
import inf.frohlich.projapi.dto.UpdateTelefoneDTO;
import inf.frohlich.projapi.model.Telefone;

import java.util.List;

public class TelefoneMapper {
    public static Telefone toEntity(TelefoneDTORequest dto) {
        if (dto == null) {
            return null;
        }

        // Converte apenas os campos permitidos do contrato de entrada
        Telefone telefone = new Telefone();
        telefone.setNumero(dto.numero());
        return telefone;
    }

    public static List<Telefone> toEntityList(List<TelefoneDTORequest> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return List.of();
        }
        return dtos.stream().map(TelefoneMapper::toEntity).toList();
    }

    public static TelefoneDTOResponse toResponse(Telefone telefone) {
        if (telefone == null) {
            return null;
        }
        return new TelefoneDTOResponse(telefone.getNumero());
    }

    public static List<TelefoneDTOResponse> toResponseList(List<Telefone> telefones) {
        if (telefones == null) {
            return List.of();
        } else {
            return telefones.stream()
                    .map(TelefoneMapper::toResponse)
                    .toList();
        }
    }

    public static void applyUpdate(Telefone entity, UpdateTelefoneDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        // Atualiza só o que o DTO permite alterar
        if (dto.numero() != null) {
            entity.setNumero(dto.numero());
        }
    }

}
