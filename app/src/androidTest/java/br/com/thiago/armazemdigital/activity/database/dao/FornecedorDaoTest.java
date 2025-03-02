package br.com.thiago.armazemdigital.activity.database.dao;

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

import br.com.thiago.armazemdigital.activity.utils.TestUtils;
import br.com.thiago.armazemdigital.database.ArmazemDigitalDb;
import br.com.thiago.armazemdigital.database.dao.FornecedorDao;
import br.com.thiago.armazemdigital.model.Fornecedor;

@RunWith(AndroidJUnit4.class)
public class FornecedorDaoTest {
    private ArmazemDigitalDb db;
    private FornecedorDao fornecedorDao;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, ArmazemDigitalDb.class)
                .allowMainThreadQueries()
                .build();
        fornecedorDao = db.fornecedorDao();
    }

    @Test
    public void insertAndRetrieve() {
        Fornecedor fornecedor = TestUtils.getFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        List<Fornecedor> fornecedoresBanco = fornecedorDao.getFornecedores(10, 0);
        Fornecedor fornecedorBanco = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNotNull("Falha ao cadastrar fornecedor", fornecedorBanco);
        assertNotNull("Falha ao cadastrar fornecedor", fornecedoresBanco);
        assertEquals("Falha ao cadastrar fornecedor", fornecedor, fornecedorBanco);
        assertEquals("Falha ao cadastrar fornecedor", 1, fornecedoresBanco.size());
    }

    @Test
    public void insertAndUpdate() {
        Fornecedor fornecedor = TestUtils.getFornecedorForTests();
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

        assertNotNull("Falha ao obter fornecedor do banco de dados", fornecedor);
        assertEquals("Falha ao atualizar fornecedor: nomes não batem", nome, fornecedor.getName());
        assertEquals("Falha ao atualizar fornecedor: emails não batem", email, fornecedor.getEmail());
        assertEquals("Falha ao atualizar fornecedor: telefones não batem", phone, fornecedor.getPhone());
        assertEquals("Falha ao atualizar fornecedor: CEPs não batem", zipCode, fornecedor.getZipCode());
        assertEquals("Falha ao atualizar fornecedor: estados não batem", state, fornecedor.getState());
        assertEquals("Falha ao atualizar fornecedor: cidades não batem", city, fornecedor.getCity());
        assertEquals("Falha ao atualizar fornecedor: endereços não batem", al1, fornecedor.getAddressLine1());
        assertEquals("Falha ao atualizar fornecedor: complementos não batem", al2, fornecedor.getAddressLine2());
        assertEquals("Falha ao atualizar fornecedor: números não batem", number, fornecedor.getNumber());
    }

    @Test
    public void insertAndDelete() {
        Fornecedor fornecedor = TestUtils.getFornecedorForTests();
        fornecedor.setId(fornecedorDao.insert(fornecedor));

        fornecedor = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNotNull("Falha ao cadastrar fornecedor", fornecedor);

        fornecedorDao.delete(fornecedor);

        fornecedor = fornecedorDao.getFornecedor(fornecedor.getId());

        assertNull("Falha ao deletar fornecedor", fornecedor);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }
}
