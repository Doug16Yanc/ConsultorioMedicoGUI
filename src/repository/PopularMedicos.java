package repository;

import entities.Medico;
import repository.MedicoRepository;

import java.sql.SQLException;
import java.util.List;

public class PopularMedicos {

    public static void popularMedicos() {
        MedicoRepository medicoRepository = new MedicoRepository();

        List<Medico> medicos = List.of(
            new Medico("Dra. Denise Yanasse", "denise@clinica.com", "(85)93939394", "senhaDenise123", "9876543214-98", "Clínica geral"),
            new Medico("Dr. Douglas Calderoni", "douglas@ortopedia.com", "(21) 91234-5678", "senhaDouglas987", "987654321-78", "Ortopedista"),
            new Medico("Dr. Alceu Scanavini", "alceu@psiquiatra.com", "(31) 99876-5432", "senhaAlceu456", "876544321-12" , "Psiquiatra"),
            new Medico("Dra. Laura Arantes", "laura@cardio.com" , "(41) 92345-6789","senhaLaura189" , "998765432-89", "Cardiologista"),
            new Medico("Dr. Thales Dalessandro","thales@hemato.com","(51) 98765-4321" ,  "senhaThales834","912345678-45", "Hematologista")
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