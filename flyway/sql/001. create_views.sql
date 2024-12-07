-- View: public.plm_tree_view

DROP VIEW IF EXISTS public.plm_tree_view;


-- View: public.plm_view

DROP VIEW IF EXISTS  public.plm_view;

CREATE OR REPLACE VIEW public.plm_view
AS
SELECT h.s_parentid,
       p.s_pk,
       p.name,
       p.oboznachenie_421975,
       c.name AS class_name
FROM plm p
         LEFT JOIN plmhierarchy h ON h.s_objectid = p.s_pk
         LEFT JOIN classes c ON p.s_classid = c.pk
         LEFT JOIN classes bc ON c.baseclassid = bc.pk;

ALTER TABLE public.plm_view
    OWNER TO postgres;


CREATE OR REPLACE VIEW public.plm_tree_view
AS
WITH RECURSIVE r AS (
    SELECT plm_view.s_parentid,
           plm_view.s_pk,
           plm_view.name,
           plm_view.oboznachenie_421975,
           plm_view.name AS class_name,
           1 AS level,
           plm_view.name::text AS path
    FROM plm_view
    WHERE plm_view.s_parentid = 1
    UNION
    SELECT plm_view.s_parentid,
           plm_view.s_pk,
           plm_view.name,
           plm_view.oboznachenie_421975,
           plm_view.name AS class_name,
           r_1.level + 1 AS level,
           (r_1.path || '\'::text) || plm_view.name::text AS path
    FROM plm_view
             JOIN r r_1 ON plm_view.s_parentid = r_1.s_pk
)
SELECT r.s_parentid,
       r.s_pk,
       r.name,
       r.oboznachenie_421975 as description,
       r.class_name,
       r.level,
       r.path
FROM r
ORDER BY r.path;

ALTER TABLE public.plm_tree_view
    OWNER TO postgres;

