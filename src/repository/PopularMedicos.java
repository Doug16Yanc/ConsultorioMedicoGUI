package repository;

import entities.Medico;
import repository.MedicoRepository;

import java.sql.SQLException;
import java.util.List;

public class PopularMedicos {

    public static void popularMedicos() {
        MedicoRepository medicoRepository = new MedicoRepository();

        List<Medico> medicos = List.of(
            new Medico("Dra. Denise Yanasse", "123456789-87", "senhaDenise123", "denise@clinica.com", "(11) 98765-4321", "Clínica geral"),
            new Medico("Dr. Douglas Calderoni", "987654321-78", "senhaDouglas987", "douglas@ortopedia.com", "(21) 91234-5678", "Ortopedista"),
            new Medico("Dr. Alceu Scanavini", "876544321-12", "senhaAlceu456", "alceu@psiquiatra.com", "(31) 99876-5432", "Psiquiatra"),
            new Medico("Dra. Laura Arantes", "998765432-89", "senhaLaura189", "laura@cardio.com", "(41) 92345-6789", "Cardiologista"),
            new Medico("Dr. Thales Dalessandro", "912345678-45", "senhaThales834", "thales@hemato.com", "(51) 98765-4321", "Hematologista")
        );

        for (Medico medico : medicos) {
            try {
                medicoRepository.cadastrarMedico(medico);
                System.out.println("Médico cadastrado: " + medico.getNome());
            } catch (SQLException e) {
                System.err.println("Erro ao cadastrar médico " + medico.getNome() + ": " + e.getMessage());
            }
        }
    }
}
