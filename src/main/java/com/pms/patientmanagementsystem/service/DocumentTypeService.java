package com.pms.patientmanagementsystem.service;

import com.pms.patientmanagementsystem.dto.DocumentTypeDto;
import com.pms.patientmanagementsystem.dto.converter.DocumentTypeDtoConverter;
import com.pms.patientmanagementsystem.exception.DocumentTypeAlreadyExistException;
import com.pms.patientmanagementsystem.exception.DocumentTypeNotFoundException;
import com.pms.patientmanagementsystem.helper.message.BusinessLogMessage;
import com.pms.patientmanagementsystem.helper.message.BusinessMessage;
import com.pms.patientmanagementsystem.model.DocumentType;
import com.pms.patientmanagementsystem.repository.DocumentTypeRepository;
import com.pms.patientmanagementsystem.request.documenttype.CreateDocumentTypeRequest;
import com.pms.patientmanagementsystem.request.documenttype.UpdateDocumentTypeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeDtoConverter converter;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository,
                               DocumentTypeDtoConverter converter) {
        this.documentTypeRepository = documentTypeRepository;
        this.converter = converter;
    }

    public void createDocumentType(final CreateDocumentTypeRequest request) {
        DocumentType documentType = new DocumentType();

        checkIfDocumentTypeExists(request.getName());

        documentType.setName(request.getName());

        documentTypeRepository.save(documentType);
        log.info(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_CREATED);
    }

    public void updateDocumentType(final String id, final UpdateDocumentTypeRequest request) {
        DocumentType documentType = findDocumentTypeByDocumentTypeId(id);

        if (!documentType.getName().equals(request.getName())) {
            checkIfDocumentTypeExists(request.getName());
        }

        documentType.setName(request.getName());

        documentTypeRepository.save(documentType);
        log.info(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_UPDATED + id);
    }

    public void deleteDocumentType(final String id) {
        documentTypeRepository.delete(findDocumentTypeByDocumentTypeId(id));
        log.info(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_DELETED + id);
    }

    public DocumentTypeDto findDocumentTypeById(final String id) {
        DocumentType documentType = findDocumentTypeByDocumentTypeId(id);

        log.info(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_FOUND + id);
        return converter.convert(documentType);
    }

    public List<DocumentTypeDto> findAllDocumentTypes() {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();

        if (documentTypes.isEmpty()) {
            log.error(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_LIST_EMPTY);
            throw new DocumentTypeNotFoundException(BusinessMessage.DocumentType.DOCUMENT_TYPE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_LIST_FOUND);
        return converter.convert(documentTypes);
    }

    protected DocumentType findDocumentTypeByDocumentTypeId(final String id) {
        return documentTypeRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_NOT_FOUND + id);
            throw new DocumentTypeNotFoundException(BusinessMessage.DocumentType.DOCUMENT_TYPE_NOT_FOUND);
        });
    }

    private void checkIfDocumentTypeExists(final String name) {
        if (documentTypeRepository.existsByNameIgnoreCase(name)) {
            log.error(BusinessLogMessage.DocumentType.DOCUMENT_TYPE_ALREADY_EXISTS + name);
            throw new DocumentTypeAlreadyExistException(BusinessMessage.DocumentType.DOCUMENT_TYPE_ALREADY_EXISTS);
        }
    }
}
