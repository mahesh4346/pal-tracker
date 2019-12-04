package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository)
    {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        System.out.println("Inside rest create Before "+timeEntryToCreate.getProjectId());
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntryToCreate);

        System.out.println("Inside rest create After " +createdTimeEntry.getProjectId() );
        return new ResponseEntity<TimeEntry>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable( "timeEntryId" ) long timeEntryId) {
        TimeEntry createdTimeEntry = timeEntryRepository.find(timeEntryId);

        if(createdTimeEntry == null)
        {
            return new ResponseEntity<TimeEntry>(createdTimeEntry, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<TimeEntry>(createdTimeEntry, HttpStatus.OK);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List list = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable( "timeEntryId" ) long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId, expected);
        if(updatedTimeEntry==null)
        {
            return new ResponseEntity<TimeEntry>(updatedTimeEntry, HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity<TimeEntry>(updatedTimeEntry, HttpStatus.OK);
        }

    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable( "timeEntryId" ) long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        TimeEntry readTimeEntry = timeEntryRepository.find(timeEntryId);
        return new ResponseEntity<TimeEntry>(readTimeEntry, HttpStatus.NO_CONTENT);

    }
}
