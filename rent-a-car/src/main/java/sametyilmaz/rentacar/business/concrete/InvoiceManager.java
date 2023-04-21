package sametyilmaz.rentacar.business.concrete;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sametyilmaz.rentacar.business.abstracts.InvoiceService;
import sametyilmaz.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import sametyilmaz.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import sametyilmaz.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import sametyilmaz.rentacar.business.dto.responses.get.invoice.GetAllInvoicesResponse;
import sametyilmaz.rentacar.business.dto.responses.get.invoice.GetInvoiceResponse;
import sametyilmaz.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import sametyilmaz.rentacar.business.rules.InvoiceBusinessRules;
import sametyilmaz.rentacar.entities.Invoice;
import sametyilmaz.rentacar.repository.InvoiceRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final ModelMapper mapper;
    private final InvoiceRepository repository;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invowices
                .stream().map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class)).toList();
        return response;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);

        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice,GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request,Invoice.class);
        invoice.setId(0);
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.map(invoice,CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);

        Invoice invoice = mapper.map(request,Invoice.class);
        invoice.setId(id);
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice,UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);

        repository.deleteById(id);
    }
}
