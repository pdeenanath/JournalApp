package net.engineeringdigest.journalApp.service;

import jakarta.transaction.Transactional;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    private static final Logger log = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


//    public void saveEntry(JournalEntry journalEntry,String userName){
//        UserEntity user = userService.findByUserName(userName);
//        JournalEntry saved = journalEntryRepository.save(journalEntry);
//        user.getJournalEntries().add(saved);
//        userService.saveEntry(user);
//    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            UserEntity user = userService.findByUserName(userName);
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + userName);
            }
            journalEntry.setUser(user); // ✅ this is critical
            journalEntryRepository.save(journalEntry);
        } catch (RuntimeException e) {

            throw new RuntimeException("An error occurred while saving my entry", e);
        }
    }


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id, String userName) {
        try {
            UserEntity user = userService.findByUserName(userName);
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry.", e);
        }

    }

}
