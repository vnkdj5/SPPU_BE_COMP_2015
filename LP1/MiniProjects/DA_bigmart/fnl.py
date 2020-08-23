#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
#get_ipython().run_line_magic('matplotlib', 'inline')
from scipy.stats import kurtosis,skew
from sklearn.linear_model import LinearRegression,Ridge
from sklearn.preprocessing import LabelEncoder,StandardScaler
from sklearn.tree import DecisionTreeRegressor,ExtraTreeRegressor
from sklearn.ensemble import RandomForestRegressor,BaggingRegressor,GradientBoostingRegressor,ExtraTreesRegressor
import xgboost,lightgbm
from sklearn.neighbors import KNeighborsRegressor
from sklearn.metrics import mean_absolute_error,mean_squared_error
from sklearn import linear_model
from sklearn.cross_validation import train_test_split
from sklearn.linear_model import ElasticNet, Lasso,  BayesianRidge, LassoLarsIC
from sklearn.ensemble import RandomForestRegressor,  GradientBoostingRegressor
from sklearn.kernel_ridge import KernelRidge
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import RobustScaler,MinMaxScaler,StandardScaler
from sklearn.model_selection import KFold, cross_val_score, train_test_split
from sklearn.metrics import mean_squared_error


# In[3]:


d1=pd.read_csv('Train_UWu5bXk.csv')
# d2=pd.read_csv('test1.csv')
# data=pd.concat([d1,d2],axis=0)
data=d1
d1.shape


# In[4]:


data['Item_Fat_Content'].value_counts()


# In[5]:


data['Item_Fat_Content']=data['Item_Fat_Content'].map({'LF':0,'Low Fat':0,'Regular':1,'low fat':0,'reg':1})


# In[6]:


data.isnull().sum()


# In[ ]:





# In[7]:


yy=data.groupby('Item_Identifier')['Item_Weight'].mean()
yy=dict(yy)
data['Item_Weight'].fillna(-1.0,inplace=True)
def ff(xx,d):
    if(d==-1.0):
        return yy[xx]
    else:
        return d
data['Item_Weight']=data.apply(lambda x : ff(x['Item_Identifier'],x['Item_Weight']),axis=1)


# In[8]:


data.isnull().sum()


# In[9]:


data.groupby('Item_Type')['Item_Weight'].median()


# In[10]:


data['Item_Weight'].fillna(10.0,inplace=True)


# In[11]:


data.groupby('Outlet_Type')['Outlet_Size'].value_counts()


# In[12]:


data.groupby(['Outlet_Type','Outlet_Size'])['Item_Outlet_Sales'].mean()


# In[13]:


data['Outlet_Size'].fillna('Small',inplace=True)
# data.Outlet_Size


# In[14]:


data['Item_MRP'].hist(bins=200,figsize=(10,6))


# In[15]:


np.where(data.Item_Visibility<=0)


# In[16]:


yy=data[data.Item_Visibility>0.0].groupby('Item_Identifier')['Item_Visibility'].mean()
yy=dict(yy)

def ff(x,y):
    if(x==0.0):
        return yy[y]
    else:
        return x
data['Item_Visibility']=data.apply(lambda x : ff(x['Item_Visibility'],x['Item_Identifier']),axis=1)


# In[17]:


ya=data.groupby('Item_Identifier')['Item_Visibility'].mean()
ya=dict(ya)

def ff(x,y):
    return (x/ya[y])
data['itemimportance']=data.apply(lambda x : ff(x['Item_Visibility'],x['Item_Identifier']),axis=1)

data['itemimportance'].describe()


# In[18]:



data['years']=2018-data['Outlet_Establishment_Year']
del data['Outlet_Establishment_Year']

lb=LabelEncoder()
lb.fit(data['Item_Type'])
data['Item_Type']=lb.transform(data['Item_Type'])
lb=LabelEncoder()


lb.fit(data['Outlet_Identifier'])
data['Outlet_Identifier']=lb.transform(data['Outlet_Identifier'])

lb=LabelEncoder()
lb.fit(data['Outlet_Size'])
data['Outlet_Size']=lb.transform(data['Outlet_Size'])

lb=LabelEncoder()
lb.fit(data['Outlet_Location_Type'])
data['Outlet_Location_Type']=lb.transform(data['Outlet_Location_Type'])

lb=LabelEncoder()
lb.fit(data['Outlet_Type'])
data['Outlet_Type']=lb.transform(data['Outlet_Type'])


# In[19]:


data.isnull().sum()


# In[20]:


data.describe()


# In[ ]:





# In[21]:


data.boxplot(by='Outlet_Type',column='Item_Outlet_Sales')


# In[ ]:





# In[22]:


del data['Item_Identifier']
test=data[8500:]
data=data[0:8500]
yd=data['Item_Outlet_Sales']
del data['Item_Outlet_Sales']
del test['Item_Outlet_Sales']
data.shape,yd.shape,test.shape


# In[23]:


yy=yd


# In[26]:


np.where(np.isnan(data))


# In[28]:


data.isnull().sum()


# In[32]:


#simple linear regression with cross validation of 5 fold
avg=0.0
skf=KFold(n_splits=5)
skf.get_n_splits(data)
for ti,tj in skf.split(data):
    dx,tx=data.iloc[ti],data.iloc[tj]
    dy,ty=yy[ti],yy[tj]
    lm=make_pipeline(MinMaxScaler(), linear_model.LinearRegression(n_jobs=-1))
    lm.fit(dx,dy)
    yu=np.sqrt(mean_squared_error(y_true=ty,y_pred=lm.predict(tx)))
    avg=avg+yu
    print(yu)

print("AVG  RMSE::",avg/5)


# In[ ]:





# In[66]:


#simple elasticnet regression with cross validation of 5 fold
avg=0.0
skf=KFold(n_splits=5)
skf.get_n_splits(data)
for ti,tj in skf.split(data):
    dx,tx=data.iloc[ti],data.iloc[tj]
    dy,ty=yy[ti],yy[tj]
    lm=make_pipeline(StandardScaler(), linear_model.ElasticNet(l1_ratio=0.6,alpha=0.001))
    lm.fit(dx,dy)
    yu=np.sqrt(mean_squared_error(y_true=ty,y_pred=lm.predict(tx)))
    avg=avg+yu
    print(yu)

print("AVG  RMSE::",avg/5)


# In[ ]:





# In[77]:


#simple Decision Tree regression with cross validation of 5 fold
avg=0.0
skf=KFold(n_splits=5)
skf.get_n_splits(data)
for ti,tj in skf.split(data):
    dx,tx=data.iloc[ti],data.iloc[tj]
    dy,ty=yy[ti],yy[tj]
    lm=DecisionTreeRegressor(max_depth=5)
    lm.fit(dx,dy)
    yu=np.sqrt(mean_squared_error(y_true=ty,y_pred=lm.predict(tx)))
    avg=avg+yu
    print(yu)

print("AVG  RMSE::",avg/5)


# In[ ]:





# In[82]:


#simple Random Forest Tree regression with cross validation of 5 fold
avg=0.0
skf=KFold(n_splits=5)
skf.get_n_splits(data)
for ti,tj in skf.split(data):
    dx,tx=data.iloc[ti],data.iloc[tj]
    dy,ty=yy[ti],yy[tj]
    lm=RandomForestRegressor(max_depth=5,n_jobs=-1,n_estimators=100)
    lm.fit(dx,dy)
    yu=np.sqrt(mean_squared_error(y_true=ty,y_pred=lm.predict(tx)))
    avg=avg+yu
    print(yu)

print("AVG  RMSE::",avg/5)


# In[ ]:




