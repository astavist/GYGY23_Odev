package sametyilmaz.rentacar.business.abstracts;

import sametyilmaz.rentacar.business.dto.requests.create.CreateCarRequest;
import sametyilmaz.rentacar.business.dto.requests.update.UpdateCarRequest;
import sametyilmaz.rentacar.business.dto.responses.create.CreateCarResponse;
import sametyilmaz.rentacar.business.dto.responses.get.car.GetAllCarsResponse;
import sametyilmaz.rentacar.business.dto.responses.get.car.GetCarResponse;
import sametyilmaz.rentacar.business.dto.responses.update.UpdateCarResponse;
import sametyilmaz.rentacar.entities.enums.State;

import java.util.List;

public interface CarService {
    List<GetAllCarsResponse> getAllByState(Boolean filter);
    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest request);
    UpdateCarResponse update(int id, UpdateCarRequest request);
    public void carMaintenance(int id, State state);
    void checkIfCarExists(int id);
    void delete(int id);
}