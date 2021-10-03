package com.pascal.bankenigma.service.Merchant;

import com.pascal.bankenigma.entity.Merchant;
import com.pascal.bankenigma.repo.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceDbImpl implements MerchantService{
    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant getDataById(String id) {
        Optional<Merchant> customerOptional = isMerchantExist(id);
        return customerOptional.get();
    }

    private Optional<Merchant> isMerchantExist(String id) {
        Optional<Merchant> customerOptional = merchantRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for merchant user id, please check and try again");
        }
        return customerOptional;
    }

    @Override
    public List<Merchant> getAllData() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant createData(Merchant merchant) {
        if (merchantRepository.existsByName(merchant.getName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Already Have Merchant with that name, please use another name");
        }
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public Merchant updateData(Merchant merchant) {
        return null;
    }
}
