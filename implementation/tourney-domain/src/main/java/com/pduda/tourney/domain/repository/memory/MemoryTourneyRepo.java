package com.pduda.tourney.domain.repository.memory;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.repository.RepoException;
import com.pduda.tourney.domain.repository.TourneyRepo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Named
//@Singleton
//@Primary
public class MemoryTourneyRepo implements TourneyRepo {

    private List<Tourney> tourneys = new ArrayList<Tourney>();

    @Override
    public void create(Tourney object) {
        tourneys.add(object);
    }

    @Override
    public Tourney createAndReturn(Tourney object) {
        tourneys.add(object);
        return object;
    }

    @Override
    public void refresh(Tourney object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void merge(Tourney object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void edit(Tourney entity) throws RepoException {
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
    public Tourney findEntity(Integer id) {
        for (Tourney tourney : tourneys) {
            if (id.equals(tourney.getId())) {
                return tourney;
            }
        }
        return null;
    }

    @Override
    public List<Tourney> findEntitiesByIds(Collection<Integer> ids) {
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
