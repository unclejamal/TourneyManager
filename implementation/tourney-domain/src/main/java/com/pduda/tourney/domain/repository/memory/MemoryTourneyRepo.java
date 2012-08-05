package com.pduda.tourney.domain.repository.memory;

import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.repository.RepoException;
import com.pduda.tourney.domain.repository.TourneyRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.context.annotation.Primary;

@Named
@Singleton
@Primary
public class MemoryTourneyRepo implements TourneyRepo {

    private List<Tournament> tourneys = new ArrayList<Tournament>();

    @Override
    public void create(Tournament object) {
        tourneys.add(object);
    }

    @Override
    public Tournament createAndReturn(Tournament object) {
        tourneys.add(object);
        return object;
    }

    @Override
    public void refresh(Tournament object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void merge(Tournament object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void edit(Tournament entity) throws RepoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List findEntities() {
        return tourneys;
    }

    @Override
    public List findEntities(int maxResults, int firstResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tournament findEntity(Integer id) {
        for (Tournament tourney : tourneys) {
            if (id.equals(tourney.getId())) {
                return tourney;
            }
        }
        return null;
    }

    @Override
    public List<Tournament> findEntitiesByIds(Collection<Integer> ids) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getEntitiesCount() {
        return tourneys.size();
    }

    @Override
    public void destroy(Integer id) throws RepoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
