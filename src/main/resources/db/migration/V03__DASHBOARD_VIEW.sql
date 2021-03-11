--Consulta Dashboard
CREATE OR REPLACE VIEW DASHBOARD_ACCOUNT AS
    SELECT
        m.id as ID_VIEW,
        m.desc_mes as MES,
        Extract('Year' From c.dt_cadastro) as ANO,
        count(c.id) as QTD_CONTAS,
        COALESCE(SUM(c.valor_conta), 0) as TOTAL_GASTOS,
        c.id_usuario as USUARIO
    FROM TB_MES m
    INNER JOIN TB_CONTA c
    ON c.id_mes = m.id
    GROUP BY ID_VIEW, ANO, USUARIO
    ORDER BY ANO, ID_VIEW;