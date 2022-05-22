package com.mbtiworld.main_api.bbs;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BbsCodeGenerator implements IdentifierGenerator {
    
    private final Logger log = LoggerFactory.getLogger(BbsCodeGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {//코드 생성 클래스

        String prefix = "BBS";

        Connection connection = session.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT LPAD(SUBSTRING(MAX(bbs_seq),4)+1,7,0) AS CDNUM FROM mw_bbs_info");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if(rs.getString("CDNUM") == null){
                    return prefix+ "0000001";
                }else{
                    return prefix+rs.getString("CDNUM");
                }
            }

        } catch (SQLException e) {
            log.error("");
            log.error("     ProductCodeGenerator.generate ::: "+e.getMessage());
            log.error("");
            e.printStackTrace();
        }
        System.out.println(" ### this is null");
        return null;
    }
}
