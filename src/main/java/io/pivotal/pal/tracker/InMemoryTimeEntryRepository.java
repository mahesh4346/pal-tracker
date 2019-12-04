package io.pivotal.pal.tracker;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private long counter=0L;
    private final Map <Long,TimeEntry> store = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        counter += 1;
        System.out.println("Counter " + counter);
        timeEntry.setId(counter);
        store.put(counter, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {

        return store.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList(store.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {

        if(store.containsKey(id)) {
            timeEntry.setId(id);
            store.put(id, timeEntry);
            return timeEntry;
        }
        else
            return null;
    }

    public void delete(long id) {
        if(store.containsKey(id))
        {
            store.remove(id);
        }
        else
        {
            System.out.println(" Requested ID does not exists" );
        }
    }
}
