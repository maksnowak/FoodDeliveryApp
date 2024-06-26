package pl.foodapp.workers;

public class DeleteWorkerService {
    private final WorkersDAO workersDAO;

    public DeleteWorkerService(WorkersDAO workersDAO) {
        this.workersDAO = workersDAO;
    }

    public void deleteWorker(int id) {
        workersDAO.deleteWorker(id);
    }
}
