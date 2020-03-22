INSERT INTO SECURITY_STATIC_DATA  (ticker, strike, type, maturity,underlying) VALUES  ('ibmCall', 40, 'call',0.5,'ibm');
INSERT INTO SECURITY_STATIC_DATA  (ticker, strike, type, maturity, underlying) VALUES  ('ibmPut', 40, 'put', 0.5,'ibm');
INSERT INTO SECURITY_STATIC_DATA  (ticker,  type) VALUES  ('ibm', 'stock');
INSERT INTO SECURITY_STATIC_DATA  (ticker, strike, type, maturity,underlying) VALUES  ('infyCall', 11, 'call',1,'infy');
INSERT INTO SECURITY_STATIC_DATA  (ticker, strike, type, maturity, underlying) VALUES  ('infyPut', 11, 'put',1,'infy');
INSERT INTO SECURITY_STATIC_DATA  (ticker,  type) VALUES  ('infy', 'stock');

INSERT INTO SECURITY_EVENT   (ticker,  price) VALUES  ('ibm', '42');
INSERT INTO SECURITY_EVENT   (ticker,  price) VALUES  ('infy', '22');