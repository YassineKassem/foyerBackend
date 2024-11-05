package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("John");
        etudiant.setPrenomEtudiant("Doe");
        etudiant.setCinEtudiant(12345678L);
    }

    @Test
    void addEtudiant_ShouldReturnEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant result = etudiantService.addEtudiant(etudiant);
        assertNotNull(result);
        assertEquals(etudiant.getNomEtudiant(), result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void retrieveEtudiant_ShouldReturnEtudiant_WhenFound() {
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));
        Etudiant result = etudiantService.retrieveEtudiant(1L);
        assertNotNull(result);
        assertEquals(etudiant.getCinEtudiant(), result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    void retrieveEtudiant_ShouldReturnNull_WhenNotFound() {
        when(etudiantRepository.findById(2L)).thenReturn(Optional.empty());
        Etudiant result = etudiantService.retrieveEtudiant(2L);
        assertNull(result);
        verify(etudiantRepository, times(1)).findById(2L);
    }

    @Test
    void removeEtudiant_ShouldCallDeleteById() {
        doNothing().when(etudiantRepository).deleteById(1L);
        etudiantService.removeEtudiant(1L);
        verify(etudiantRepository, times(1)).deleteById(1L);
    }

    @Test
    void modifyEtudiant_ShouldReturnUpdatedEtudiant() {
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant result = etudiantService.modifyEtudiant(etudiant);
        assertNotNull(result);
        assertEquals(etudiant.getNomEtudiant(), result.getNomEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void recupererEtudiantParCin_ShouldReturnEtudiant_WhenFound() {
        when(etudiantRepository.findEtudiantByCinEtudiant(12345678L)).thenReturn(etudiant);
        Etudiant result = etudiantService.recupererEtudiantParCin(12345678L);
        assertNotNull(result);
        assertEquals(etudiant.getCinEtudiant(), result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(12345678L);
    }
}
