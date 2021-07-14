package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.constants.TransferType;
import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
public class transferServiceTest {

    @Autowired
    ITransferService transferService;

    @Autowired
    IUserService userService;

    public Factory factory  = new Factory();

    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();

    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveTransferCredit")
    public void saveTransferCreditTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userBeneficiary = factory.constructUser(listUserDTO.get(2));
        Double walletBefore = userBeneficiary.getWallet();
        TransferDTO transferDTO = new TransferDTO();
        //WHEN
        transferDTO = transferService.addTransfer("FR 1231 1213 1313", TransferType.CREDIT_WALLET.toString(),
                200.0, userBeneficiary);
        Double newWallet = userService.findUserById(userBeneficiary.getId()).getWallet();
        //THEN
        assertTrue( newWallet == walletBefore + 200 );
        assertTrue( transferDTO.getIdTransfer() > 0);
    }

    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveTransferDebit")
    public void saveTransferDeditOkTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userBeneficiary = factory.constructUser(listUserDTO.get(3));
        Double walletBefore = userBeneficiary.getWallet();
        TransferDTO transferDTO = new TransferDTO();
        //WHEN
        transferDTO = transferService.addTransfer("FR 1231 1213 1313", TransferType.DEBIT_WALLET.toString(),
                100.0, userBeneficiary);
        Double newWallet = userService.findUserById(userBeneficiary.getId()).getWallet();
        //THEN
        assertTrue( newWallet == walletBefore - 100 );
        assertTrue( transferDTO.getIdTransfer() > 0);
    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @Transactional
    @DisplayName("Test saveTransferDebit with Transactionnal")
    public void saveTransferDeditOkWithTransactionalTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userBeneficiary = factory.constructUser(listUserDTO.get(3));
        Double walletBefore = userBeneficiary.getWallet();
        Pageable pageable = null;
        Page<TransferDTO> listTransferBefore = transferService.findAllByUser(factory.constructUserDTO(userBeneficiary),pageable);
        System.out.println("Avant : " + walletBefore + "  nbElements:" + listTransferBefore.getTotalElements());
        TransferDTO transferDTO = new TransferDTO();
        //WHEN
        transferDTO = transferService.addTransfer("FR 3333 1213 1313", TransferType.DEBIT_WALLET.toString(),
                50.0, userBeneficiary);
        Double newWallet = userService.findUserById(userBeneficiary.getId()).getWallet();
        pageable = null;
        Page<TransferDTO> listTransferAfter = transferService.findAllByUser(factory.constructUserDTO(userBeneficiary),pageable);
        System.out.println("apres : " + newWallet+ "  nbElements:" + listTransferAfter.getTotalElements());
        //THEN
        assertEquals( newWallet, walletBefore);
        assertEquals( listTransferAfter.getTotalElements(), listTransferBefore.getTotalElements());
    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveTransferDeditNotPossible")
    public void saveTransferDeditNotPossibleTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userBeneficiary = factory.constructUser(listUserDTO.get(1));
        Double walletBefore = userBeneficiary.getWallet();
        TransferDTO transferDTO = new TransferDTO();
        //WHEN
        //THEN
        assertThrows(DataNotConformException.class, () -> transferService.addTransfer("FR 1231 1213 1313", TransferType.DEBIT_WALLET.toString(),
                1000.0, userBeneficiary));
        Double newWallet = userService.findUserById(userBeneficiary.getId()).getWallet();
        assertEquals( newWallet,  walletBefore );
    }

    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test findAllByUser")
    public void findAllByUserTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        UserDTO userDTO = listUserDTO.get(1);
        int page = 0;
        //WHEN
        Page<TransferDTO> pagesTransfer = transferService.findAllByUser(userDTO,  PageRequest.of(page,2));
        //THEN
        assertEquals(2,pagesTransfer.getContent().size());
    }

    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test theLastThreeTransfers")
    public void theLastThreeTransfersTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        UserDTO userDTO = listUserDTO.get(1);
        int page = 0;
        //WHEN
        Page<TransferDTO> pagesTransferDTO = transferService.theLastThreeTransfers(userDTO,  PageRequest.of(page,2));
        //THEN
        assertEquals(2,pagesTransferDTO.getContent().size());
        assertEquals(2,pagesTransferDTO.getTotalElements());
    }
    //--------------------------------------------------------------------------------------------------------

}
