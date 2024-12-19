package com.accounting.einvoices.service.impl.unit;

import com.accounting.einvoices.dto.CompanyDTO;
import com.accounting.einvoices.dto.InvoiceDTO;
import com.accounting.einvoices.dto.InvoiceProductDTO;
import com.accounting.einvoices.entity.Company;
import com.accounting.einvoices.entity.Invoice;
import com.accounting.einvoices.enums.Currency;
import com.accounting.einvoices.repository.InvoiceRepository;
import com.accounting.einvoices.service.CompanyService;
import com.accounting.einvoices.service.InvoiceProductService;
import com.accounting.einvoices.service.impl.InvoiceServiceImpl;
import com.accounting.einvoices.util.MapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.accounting.einvoices.enums.InvoiceStatus.APPROVED;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceImplTest {


    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    CompanyService companyService;

    @Mock
    InvoiceProductService invoiceProductService;

    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Test
    void testFindAllByAcceptDate() {

        int year = 2024;
        int startMonth = 3;
        int endMonth = 9;
        Long companyId = 1L;

        Company company = new Company();
        company.setId(1L);
        company.setTitle("Company");

        CompanyDTO companyDTO = new CompanyDTO();
        company.setId(1L);
        companyDTO.setTitle("Company");

        when(companyService.getByLoggedInUser()).thenReturn(companyDTO);


        Invoice invoiceEntity1 = new Invoice();
        invoiceEntity1.setInvoiceNo("N1");
        invoiceEntity1.setAcceptDate(LocalDateTime.of(2024, 1, 1, 12, 0));
        invoiceEntity1.setCurrency(Currency.USD);
        invoiceEntity1.setInvoiceStatus(APPROVED);
        invoiceEntity1.setCompany(company);

        Invoice invoiceEntity2 = new Invoice();
        invoiceEntity2.setInvoiceNo("N2");
        invoiceEntity2.setAcceptDate(LocalDateTime.of(2024, 3, 1, 12, 0));
        invoiceEntity2.setCurrency(Currency.GEL);
        invoiceEntity2.setInvoiceStatus(APPROVED);
        invoiceEntity2.setCompany(company);

        Invoice invoiceEntity3 = new Invoice();
        invoiceEntity3.setInvoiceNo("N3");
        invoiceEntity3.setAcceptDate(LocalDateTime.of(2024, 5, 1, 12, 0));
        invoiceEntity3.setCurrency(Currency.EUR);
        invoiceEntity3.setInvoiceStatus(APPROVED);
        invoiceEntity3.setCompany(company);

        List<Invoice> invoiceEntityList = List.of(invoiceEntity1, invoiceEntity2, invoiceEntity3);

        InvoiceDTO invoiceDTO1 = new InvoiceDTO();
        invoiceDTO1.setInvoiceNo("N1");
        invoiceDTO1.setAcceptDate(LocalDateTime.of(2024, 1, 1, 12, 0));
        invoiceDTO1.setCurrency(Currency.USD);
        invoiceDTO1.setInvoiceStatus(APPROVED);
        invoiceDTO1.setCompany(companyDTO);

        InvoiceDTO invoiceDTO2 = new InvoiceDTO();
        invoiceDTO2.setInvoiceNo("N1");
        invoiceDTO2.setAcceptDate(LocalDateTime.of(2024, 3, 1, 12, 0));
        invoiceDTO2.setCurrency(Currency.GEL);
        invoiceDTO2.setInvoiceStatus(APPROVED);
        invoiceDTO2.setCompany(companyDTO);

        InvoiceDTO invoiceDTO3 = new InvoiceDTO();
        invoiceDTO3.setInvoiceNo("N1");
        invoiceDTO3.setAcceptDate(LocalDateTime.of(2024, 5, 1, 12, 0));
        invoiceDTO3.setCurrency(Currency.EUR);
        invoiceDTO3.setInvoiceStatus(APPROVED);
        invoiceDTO3.setCompany(companyDTO);

        List<InvoiceDTO> invoiceDTOList = List.of(invoiceDTO1, invoiceDTO2, invoiceDTO3);

        InvoiceProductDTO invoiceProductDTO1 = new InvoiceProductDTO();
        invoiceProductDTO1.setInvoice(invoiceDTO1);

        InvoiceProductDTO invoiceProductDTO2 = new InvoiceProductDTO();
        invoiceProductDTO2.setInvoice(invoiceDTO2);

        InvoiceProductDTO invoiceProductDTO3 = new InvoiceProductDTO();
        invoiceProductDTO3.setInvoice(invoiceDTO3);


        when(invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO1.getId()))
                .thenReturn(List.of(invoiceProductDTO1));
        when(invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO2.getId()))
                .thenReturn(List.of(invoiceProductDTO2));
        when(invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(invoiceDTO3.getId()))
                .thenReturn(List.of(invoiceProductDTO3));


        when(invoiceRepository.findAllByYearAndMonthBetweenAndStatusAndCompany(eq(2024), eq(3), eq(9), eq(APPROVED), any()))
                .thenReturn(invoiceEntityList);

        for (int i = 0; i < invoiceEntityList.size(); i++) {
            Invoice invoice = invoiceEntityList.get(i);
            InvoiceDTO invoiceDTO = invoiceDTOList.get(i);
            when(mapperUtil.convert(eq(invoice), any(InvoiceDTO.class))).thenReturn(invoiceDTO);
        }

        Map<Currency, List<InvoiceDTO>> map = invoiceService.findAllByAcceptDate(year, startMonth, endMonth);

        assertNotNull(map);
        assertEquals(3, map.size());

        assertTrue(map.containsKey(Currency.USD));
        assertTrue(map.containsKey(Currency.GEL));
        assertTrue(map.containsKey(Currency.EUR));

        assertEquals(1, map.get(Currency.USD).size());
        assertEquals(1, map.get(Currency.GEL).size());
        assertEquals(1, map.get(Currency.EUR).size());

        assertEquals("N1", map.get(Currency.USD).get(0).getInvoiceNo());
        assertEquals("N2", map.get(Currency.GEL).get(0).getInvoiceNo());
        assertEquals("N3", map.get(Currency.GEL).get(0).getInvoiceNo());
    }

    @Test
    void testSetPriceTaxTotal() {
        InvoiceProductDTO invoiceProductDTO1 = new InvoiceProductDTO();
        invoiceProductDTO1.setPrice(valueOf(100));
        invoiceProductDTO1.setTax(valueOf(20));
        invoiceProductDTO1.setTotal(valueOf(120));

        InvoiceProductDTO invoiceProductDTO2 = new InvoiceProductDTO();
        invoiceProductDTO2.setPrice(valueOf(200));
        invoiceProductDTO2.setTax(valueOf(40));
        invoiceProductDTO2.setTotal(valueOf(240));

        InvoiceProductDTO invoiceProductDTO3 = new InvoiceProductDTO();
        invoiceProductDTO3.setPrice(null);
        invoiceProductDTO3.setTax(null);
        invoiceProductDTO3.setTotal(null);

        List<InvoiceProductDTO> mockInvoiceProducts = List.of(invoiceProductDTO1, invoiceProductDTO2, invoiceProductDTO3);

        when(invoiceProductService.findAllByInvoiceIdAndCalculateTotalPrice(anyLong()))
                .thenReturn(mockInvoiceProducts);


        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setId(1L);

        invoiceService.setPriceTaxTotal(invoice);

        assertEquals(valueOf(300.00), invoice.getPrice(), "The total price should be 300");
        assertEquals(valueOf(60.00), invoice.getTax(), "The total tax should be 60");
        assertEquals(valueOf(360.00), invoice.getTotal(), "The total amount should be 360");
    }

}