package br.com.thiago.armazemdigital.database.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;
import br.com.thiago.armazemdigital.model.Fornecedor;
import br.com.thiago.armazemdigital.utils.AndroidTestUtils;

@RunWith(AndroidJUnit4.class)
public class FornecedorDaoTest {
    private ArmazemDigitalDb db;
    private FornecedorDao fornecedorDao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        fornecedorDao = db.fornecedorDao();
    }

    @Test
    public void insertAndRetrieve() {
        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        List<Fornecedor> fornecedoresBanco = fornecedorDao.getFornecedores();
        Fornecedor fornecedorBanco = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNotNull(fornecedorBanco);
        assertNotNull(fornecedoresBanco);
        assertEquals(fornecedor, fornecedorBanco);
        assertEquals(1, fornecedoresBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        String nome = "Outro nome";
        String email = "outro_email@teste.com.br";
        String phone = "(11) 90000-0000";
        String zipCode = "12345-678";
        String state = "Rio de Janeiro";
        String city = "Rio de Janeiro";
        String al1 = "Avenida de Exemplo";
        String al2 = "Casa 2";
        String number = "1234";

        fornecedor.setName(nome);
        fornecedor.setEmail(email);
        fornecedor.setPhone(phone);
        fornecedor.setZipCode(zipCode);
        fornecedor.setState(state);
        fornecedor.setCity(city);
        fornecedor.setAddressLine1(al1);
        fornecedor.setAddressLine2(al2);
        fornecedor.setNumber(number);
        fornecedor.setDateCreated(new Date());

        fornecedorDao.update(fornecedor);

        fornecedor = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNotNull(fornecedor);
        assertEquals(nome, fornecedor.getName());
        assertEquals(email, fornecedor.getEmail());
        assertEquals(phone, fornecedor.getPhone());
        assertEquals(zipCode, fornecedor.getZipCode());
        assertEquals(state, fornecedor.getState());
        assertEquals(city, fornecedor.getCity());
        assertEquals(al1, fornecedor.getAddressLine1());
        assertEquals(al2, fornecedor.getAddressLine2());
        assertEquals(number, fornecedor.getNumber());
    }

    @Test
    public void insertAndDelete() {
        Fornecedor fornecedor = AndroidTestUtils.createFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        fornecedor = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNotNull(fornecedor);

        fornecedorDao.delete(fornecedor);

        fornecedor = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNull(fornecedor);
    }

    @After
    public void tearDown() {
        db.close();
    }
}
