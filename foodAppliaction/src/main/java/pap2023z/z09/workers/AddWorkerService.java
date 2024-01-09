package pap2023z.z09.workers;

public class AddWorkerService {
    private final WorkersDAO workersDAO;

    public AddWorkerService(WorkersDAO workersDAO) {
        this.workersDAO = workersDAO;
    }

    public void addWorker(WorkersDTO worker) {
        workersDAO.addWorker(worker.getWorker(), worker.getRestaurantId());
    }
}
