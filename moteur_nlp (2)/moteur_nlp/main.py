
import torch, torch.nn as nn  
import json
import csv
from pandas import DataFrame
import path
import re
import random
import numpy as np

import pandas as pd

import spacy
nlp=spacy.load('fr_core_news_lg')




from collections import Counter
from torch.utils.data import Dataset, DataLoader



import fasttext
import fasttext.util
ft = fasttext.load_model('weight_vectors\\cc.fr.300.bin')


#################################### METHODES DE PRETRAITEMENT ######################################
#params => sentence : string
#returns => objet Doc : spacy.tokens.doc.Doc
def get_doc(sentence):
    doc = nlp(sentence)
    return doc

#donne la liste des phrases(separées par un ".","?","!", ...)
#params => text:spacy.tokens.doc.Doc
#returns => list (liste de span (une partie d'un objet Doc))
def get_sentences(text):
    sentences = []
    for sentence in text.sents:
        sentences.append(sentence)
    return sentences

tokenizer = nlp.Defaults.create_tokenizer(nlp)

# donne la liste des tokens sous forme de string
#params => doc : objet Doc
#returns => list 
def list_tokens(doc):
    list=[]
    for token in doc:
        list.append(str(token))
    return list

# donne la liste des tokens sous forme de string
#params => text : string
#returns => list 
def get_tokens(text):
    tokens = list_tokens(tokenizer(text))
    return tokens


def removeStopwords(list):
	nlp.Defaults.stop_words -= list
	return nlp.Defaults.stop_words

def get_nonPunctuation(doc):
    return [w for w in doc if w.pos_ != "PUNCT"]


def clean_doc(doc):
    tab = get_nonStopwords(doc)
    tab2= get_nonPunctuation(doc)
    return list(set.intersection(set(tab), set(tab2)))


#lematization : donne la racine des mots
def get_lema(tags):
    lema=[]
    for token in tags:
        lema.append((token.lemma_))
    return lema

# POS-Tagging
def get_pos(tags):
    pos=[]
    for token in tags:
        pos.append(token.pos_)
    return pos

def print_dependances_jup(doc):
    displacy.render(doc, style='dep', jupyter=True, options={'distance': 130})
    
def get_NER(doc):
    list = {}
    for w in doc.ents:
        if(w.label_)=="PER":
            list[w.text]="personne"
        if(w.label_)=="LOC":
            list[w.text]="site ou lieu qui n'est pas une ville, comme les chaîne de montagne, des rivières"
        if(w.label_)=="ORG":
            list[w.text]="organisation, agence ou institut"
        if(w.label_)=="TIME":
            list[w.text]="temps"
        if(w.label_)=="MONEY":
            list[w.text]="monnaie"
        if(w.label_)=="MISC":
            list[w.text]="entité diverse"
        if(w.label_)=="GPE":
            list[w.text]="pays, ville ou état"
        if(w.label_)=="NORP":
            list[w.text]="nationalité ou groupe religieux ou politique"    
    return list


####### AUTRE FONCTIONS POUR NER ########

# est une langue
langues = pd.read_csv("data\\liste_des_langues_du_monde.csv",header=None)
langues = langues[0]
langues.columns = ["langues"]
langues
def is_langue(mot,langues):
    for i in langues:
        i=i.lower()
        mot=mot.lower()
        if(i==mot):
            return 1
    return 0
def toutes_les_langues(langues=langues):
    toutes_langues = []
    for i in langues:
        i=i.lower()
        toutes_langues.append(i)
    return toutes_langues


# est une wilaya d'algerie
wilayas = pd.read_csv("data\\Wilayas.csv",header=None)
wilayas.columns = ["code","nom"]
def is_wilaya(mot,wilayas=wilayas):
    for i in wilayas["nom"]:
        i=i.lower()
        mot=mot.lower()
        if(i==mot):
            return 1
    return 0
def toutes_les_wilayas(wilayas=wilayas):
    toutes_wilayas = []
    for i in wilayas["nom"]:
        i=i.lower()
        toutes_wilayas.append(i)
    return toutes_wilayas


# est une commune d'une wilaya d'algerie
communes = pd.read_csv("data\\communes.csv",header=None)
communes.columns = ["code_postal","nom","wilaya_id"]
def is_commune(mot,communes=communes):
    for c,i in enumerate(communes["nom"]):
        i=i.lower()
        mot=mot.lower()
        if(i==mot):
            return communes["wilaya_id"][c]
    return 0

def get_wilaya_commune(commune,communes=communes,wilayas=wilayas):
    res=is_commune(commune)
    if res!=0:
        return wilayas["nom"][res-1]
    
def toutes_les_communes(communes=communes):
    toutes_communes = []
    for i in communes["nom"]:
        i=i.lower()
        toutes_communes.append(i)
    return toutes_communes


# est un pays
pays = pd.read_csv("data\\pays.csv",header=None)
pays.columns = ["nom"]
def is_pays(mot,pays=pays):
    for c,i in enumerate(pays["nom"]):
        i=i.lower()
        mot=mot.lower()
        if(i==mot):
            return 1
    return 0

def tous_les_pays(pays=pays):
    tous_pays = []
    for i in pays["nom"]:
        i=i.lower()
        tous_pays.append(i)
    return tous_pays


def named_entity_rec(phrase,pays=pays,wilayas=wilayas,communes=communes):
    doc = get_doc(phrase)
    ner2 = get_NER(doc)
    phrase= phrase.lower()
    word_list = phrase.split()
    list_ner={}
    for i in toutes_les_wilayas():
        x= " "+i+" "
        z= "'"+i+" "
        y= i+" "
        w= " "+i
        if phrase.find(x)!=-1 or (phrase.find(y)!=-1 and i.split()[0]== word_list[0]) or phrase.find(z)!=-1 or (phrase.find(w)!=-1 and i.split()[-1]== word_list[-1]):
            st = i+" : wilaya d'Algérie"
            list_ner[i]=(st)
    for i in toutes_les_communes():
        x= " "+i+" "
        z= "'"+i+" "
        y= i+" "
        w= " "+i
        if phrase.find(x)!=-1 or (phrase.find(y)!=-1 and i.split()[0]== word_list[0]) or phrase.find(z)!=-1 or (phrase.find(w)!=-1 and i.split()[-1]== word_list[-1]):
            st = i+" : commune de la wilaya "+get_wilaya_commune(i)
            list_ner[i]=(st)
    for i in tous_les_pays():
        x= " "+i+" "
        z= "'"+i+" "
        y= i+" "
        w= " "+i
        if phrase.find(x)!=-1 or (phrase.find(y)!=-1 and i.split()[0]== word_list[0]) or phrase.find(z)!=-1 or (phrase.find(w)!=-1 and i.split()[-1]== word_list[-1]):
            st = i+" : pays"
            list_ner[i]=(st)
    for i in toutes_les_langues():
        x= " "+i+" "
        z= "'"+i+" "
        y= i+" "
        w= " "+i
        if phrase.find(x)!=-1 or (phrase.find(y)!=-1 and i.split()[0]== word_list[0]) or phrase.find(z)!=-1 or (phrase.find(w)!=-1 and i.split()[-1]== word_list[-1]):
            st = i+" : langue, ou personne d'origine d'un pays qui parle cette langue"
            list_ner[i]=(st)
    for y in list_ner:
        if y in ner2:
            del ner2[y]
    return ner2,list_ner
######  PRETRAITEMENT TRAIN DATA


#loading the data
train = pd.read_csv("data\\train_1_2.csv",header=None)
valid = pd.read_csv("data\\valid_1_2.csv",header=None)


###################################### TRAIN DATA ####################################################


###### GET TRAIN DATA

train = train[[0, 1]]
train.columns = ["sentence","intent"]
train


###### TOKENIZATION
import string

def text_tokenizer(text):
    regex = re.compile('[0-9]') # remove numbers
    nonumbers = regex.sub("", text.lower())
    doc=nlp(nonumbers)
    list=[]
    for token in doc:
        if((token.pos_ != "PUNCT") and (token.is_stop==False) and (str(token)!=" ")):
            str_token=str(token)
            str_token=str_token.replace("  ",'')
            x= re.findall("^-", str_token)
            if x:
                y=str_token[0]
                str_token=str_token.replace(y,"")
            if(str_token !=''):
                list.append(str_token)
    if(len(list)==0):
        print(doc, " liste vide !")              
    return list 

#tokenize label
def label_tokenizer(intent):
    doc=nlp(intent)
    liste=[]
    for token in doc:
        liste.append(str(token)) 
    return liste


###### ENCODAGE 

#fait la vectorisation d'une phrase
#param : sentence : list de mots(tokens)
# returns : list of numpy

def encode_sentence(sentence_tokenized):
    sentence_len=len(sentence_tokenized)
    sentence_matrix=np.zeros((sentence_len,100))
    for i,w in enumerate(sentence_tokenized):
       
        try:
            vec=ft.get_word_vector(w)
            sentence_matrix[i]=vec
        except KeyError:
                sentence_matrix[i]=np.random.normal(scale=0.6,size=(100,))
    return sentence_matrix,sentence_len


def encode_label(label_tokenized):
    label_len=len(label_tokenized)
    mat_label=np.zeros((label_len,100))
    for i,w in enumerate(label_tokenized):
        try:
            mat_label[i]=ft.get_word_vector(w)
        except KeyError:
            mat_label[i]=np.random.normal(scale=0.6,size=(100,))

    return mat_label,label_len



#list de sentence tokens
train["sentence_tokens"] = train["sentence"].apply(lambda x: text_tokenizer(x))
#taille liste tokens
train["sentence_len"] = train["sentence_tokens"].apply(lambda x: len(x))
#liste intent tokens
train["intent_tokens"] = train["intent"].apply(lambda x: label_tokenizer(x))
#taille intent tokens
train["intent_len"] = train["intent_tokens"].apply(lambda x: len(x))



# enlever les lignes dont les phrases sont vides
train=train[(train["sentence_len"]) != 0]
train


###############################################   indexes ########################################""




intents = train["intent"]
counts_intent = Counter(intents)
intents = counts_intent.keys()



label2index={w:(i+1) for i,w in enumerate(intents)}
label2index["UNK"]=0
def label_to_index(lab,label2index):
    if(lab not in label2index):
        label2index[lab]=len(label2index)+1
    return label2index[lab]


############################################################################################## 
label2index2={w:(i) for i,w in enumerate(intents)}

############################################################################################## 
train["index_intent"] = train["intent"].apply(lambda x:(label_to_index(x,label2index2)))
train
############################################################################################## 

counts = Counter()
for index, row in train.iterrows():
    counts.update(row["sentence_tokens"])


#creating vocabulary
vocab2index = {"UNK":1}
words = ["UNK"]
for word in counts:
    vocab2index[word] = len(words)+1
    words.append(word)
vocab_size=len(words)


###############################################

labels = train["intent_tokens"]
def get_intent_vocab(labels):
    intent_vocab={}
    words_label=["UNK"]
    for l in labels:
        
        for t in l:
            t=t.lower()
            if(t not in words_label):
                words_label.append(t)
                intent_vocab[t]=len(words_label)-1
    intent_vocab["UNK"]=0           
    return intent_vocab,words_label
intent_vocab , words_label = get_intent_vocab(labels)


###############################################


index2label={}
for i,l in enumerate(label2index):
    index2label[i+1]=l
index2label[0]="UNK"

###############################################

index2label_vocab={}
for i,l in enumerate(intent_vocab):
    if(l!='UNK'):
        index2label_vocab[i+1]=l
index2label_vocab[0]="UNK"

###############################################

def get_word2idx(word,vocab_index):
    return vocab_index[word]


############################################################################################## 



def encode_sentence(text, vocab2index, N=20):
    
    encoded = np.zeros(N, dtype=int)
    enc1 = np.array([vocab2index.get(word, vocab2index["UNK"]) for word in text])
    length = min(N, len(enc1))
    encoded[:length] = enc1[:length]
    return encoded, length



train["encoded_sent"] = train["sentence_tokens"].apply(lambda x: encode_sentence(x, vocab2index))

#train["encoded_intent"] = train["intent_tokens"].apply(lambda x: encode_label(x, intent_vocab))
train


# vecteurs fasttext

def encoded_sentence(sentence,vocab2index):
    sentence_len=len(sentence)
    
    sentence_matrix=np.zeros((sentence_len))
    for i,w in enumerate(sentence):
        cpt=len(vocab2index)
        try:
            idx=vocab2index[w]
            sentence_matrix[i]=idx
        except KeyError:
            cpt=cpt+1
            vocab2index[w]=cpt
            print(len(vocab2index))
            sentence_matrix[i]=vocab2index[w]
    return sentence_matrix,sentence_len

def encoded_label(label,label2index):
    label_len=len(label)
    
    label_matrix=np.zeros((label_len))
    for i,w in enumerate(label):
        cpt=len(label2index)
        try:
            idx=label2index[w]
            label_matrix[i]=idx
        except KeyError:
            cpt=cpt+1
            label2index[w]=cpt
            print(len(label2index))
            label_matrix[i]=label2index[w]
    return label_matrix,label_len


###################################### VALID DATA ####################################################



###### GET VALID DATA

valid = valid[[0, 1]]
valid.columns = ["sentence","intent"]

#list de sentence tokens
valid["sentence_tokens"] = valid["sentence"].apply(lambda x: text_tokenizer(x))
#taille liste tokens
valid["sentence_len"] = valid["sentence_tokens"].apply(lambda x: len(x))
#liste intent tokens
valid["intent_tokens"] = valid["intent"].apply(lambda x: label_tokenizer(x))
#taille intent tokens
valid["intent_len"] = valid["intent_tokens"].apply(lambda x: len(x))
valid 



# enlever les lignes dont les phrases sont vides
valid=valid[(valid["sentence_len"]) != 0]
valid




def get_label_name(i,labels=label2index2):
    res="other"
    for j in labels:
        if labels[j]==i:
            res=j
    return res       



valid["index_intent"] = valid["intent"].apply(lambda x:(label_to_index(x,label2index2)))
valid



valid["encoded_sent"] = valid["sentence_tokens"].apply(lambda x: encode_sentence(x, vocab2index))

#valid["encoded_intent"] = valid["intent_tokens"].apply(lambda x: encode_label(x, intent_vocab))
valid



#########################################  LOAD DATA  #########################################



x_train = list(train["encoded_sent"])
y_train = list(train["index_intent"])

x_valid = list(valid["encoded_sent"])
y_valid = list(valid["index_intent"])



class SentencesDataset(Dataset):
    def __init__(self, X, Y):
        self.x = X
        self.y = Y
        
    def __len__(self):
        return len(self.y)
    
    def __getitem__(self, idx):
        #x->tensor(len(x)*100),x_len->int,y->tensor(len_y*100),y_length->int
        encoded_sent=torch.from_numpy(self.x[idx][0])
        index_intent=self.y[idx]
        len_sent=self.x[idx][1]
        return encoded_sent, index_intent, len_sent


train_data = SentencesDataset(x_train, y_train)
valid_data = SentencesDataset(x_valid, y_valid)



batch_size = 10
train_dl = DataLoader(train_data, batch_size=batch_size, shuffle=True)
valid_dl = DataLoader(valid_data, batch_size=batch_size, shuffle=True)


#########################################  BUILD MODEL  #########################################

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
import torch.nn.functional as F


class LSTM(torch.nn.Module) :
    def __init__(self, vocab_size, embedding_dim, hidden_dim, fasttext_weights, dropout) :
        super().__init__()
        self.embeddings = nn.Embedding(vocab_size, embedding_dim, padding_idx=0)
        self.embeddings.weight.data.copy_(torch.from_numpy(fasttext_weights))
        self.embeddings.weight.requires_grad = False ## freeze embeddings
        self.lstm = nn.LSTM(embedding_dim, hidden_dim, batch_first=True)
        self.linear = nn.Linear(hidden_dim,145)
        self.dropout = nn.Dropout(dropout)
       
    def forward(self, x):
        x = self.embeddings(x)
        x = self.dropout(x)
        lstm_out, (ht,ct)= self.lstm(x)
        
        out = self.linear(ht[-1])
        return out


def get_emb_matrix(word_counts, emb_size = 300):
    """ Creates embedding matrix from word vectors"""
    vocab_size = len(word_counts) + 2
    vocab_to_idx = {}
    vocab = ["", "UNK"]
    W = np.zeros((vocab_size, emb_size), dtype="float32")
    W[0] = np.zeros(emb_size, dtype='float32') # adding a vector for padding
    W[1] = np.random.uniform(-0.25, 0.25, emb_size) # adding a vector for unknown words 
    vocab_to_idx["UNK"] = 1
    i = 2
    for word in word_counts:
        try:
            W[i] = ft.get_word_vector(word)
        except KeyError:
            W[i] = np.random.uniform(-0.25,0.25, emb_size)
        vocab_to_idx[word] = i
        vocab.append(word)
        i += 1
    return W, np.array(vocab), vocab_to_idx

pretrained_weights, vocab, vocab2index = get_emb_matrix(counts)


hidden_dim=100



model = LSTM(len(vocab), 300, hidden_dim, pretrained_weights,dropout=0.2)



########################################### Test Data ############################################


###### GET TRAIN DATA
test = pd.read_csv("data\\test1_2.csv",header=None)
test = test[[0, 1]]
test.columns = ["sentence","intent"]
test["index_intent"] = test["intent"].apply(lambda x:(label_to_index(x,label2index2)))



test_shuffled=test.sample(frac=1).reset_index(drop=True)

#load weights
path='Model\\saved_weights_1_2_hid1100_3.pt'
model.load_state_dict(torch.load(path));
model.eval();


#inference 


def predict(model, sentence):
    tokenized = text_tokenizer(sentence)
    #print(tokenized)
    indexed = encode_sentence(tokenized, vocab2index)
    #print(indexed)
    sent=torch.from_numpy(indexed[0])
    sent=sent.long()
    sent=sent.reshape(1,20)
    #print(sent,sent.dim(),sent.shape)
    prediction = model(sent)
    #print(prediction)
    pred = torch.max(prediction, 1)[1]
    #print(pred.item())
    return pred.item(),get_label_name(pred.item())  

def tester(model,data_test):
    cpt=0
    intent_true=[]
    intent_pred=[]
    for i in data_test.index:
        res = predict(model,data_test["sentence"][i])
        intent_pred.append(res[0])
        intent_true.append(data_test["index_intent"][i])
        if res[0]==data_test["index_intent"][i]:
            #print("pred = ",res)
            #print("intent =",data_test["intent"][i])
            cpt=cpt+1
            print(cpt/len(data_test))
    return cpt/len(data_test),intent_true,intent_pred



# In[ ]:


##################################### INTERFACE GRAPHIQUE #############################################
#Creating tkinter GUI
import tkinter
from tkinter import * 
import tkinter as tk
from tkinter import ttk

    
def pred(model=model):
    sentence = entrée.get("1.0",'end-1c').strip()
    réponse2.delete("0.0",END)
    entrée.delete("0.0",END)
    l.delete("0.0",END)
    réponse.delete("0.0",END)
    if sentence != '':
        réponse.config(state=NORMAL)
        l.config(state=NORMAL)
        réponse2.config(state=NORMAL)
        l.insert(END,sentence)
        tokenized = text_tokenizer(sentence)
        #print(tokenized)
        indexed = encode_sentence(tokenized, vocab2index)
        #print(indexed)
        sent=torch.from_numpy(indexed[0])
        sent=sent.long()
        sent=sent.reshape(1,20)
        #print(sent,sent.dim(),sent.shape)
        prediction = model(sent)
        #print(prediction)
        pred = torch.max(prediction, 1)[1]
        res = get_label_name(pred.item())
        réponse.insert(END,  res )
        
        ############### NER##################
        
        réponse2.config(state=NORMAL)
        ner2, list_ner=named_entity_rec(sentence)
        réponse2.delete("0.0",END)
        if len(list_ner)== 0 and len(ner2) == 0:  
            réponse2.insert(END, "Votre phrase ne contient pas des entités nommées ! \n")
        else:
            for i in list_ner:
                a=list_ner[i]
                réponse2.insert(END,  a + '\n')
            for k in ner2:
                b = k +" : "+ner2[k]
                réponse2.insert(END,  b + '\n')
        réponse2.config(state=DISABLED)
        réponse2.yview(END)
              
root = tk.Tk()
root.title("Moteur NLP de VAZII BOX")
root.geometry("700x557")
root.resizable(width=FALSE, height=FALSE)
root.iconbitmap("img\\logo.ico")

réponse = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
réponse.config(state=DISABLED)

def resize_image(event):
    new_width = event.width
    new_height = event.height
    image = copy_of_image.resize((new_width, new_height))
    photo = ImageTk.PhotoImage(image)
    label.config(image = photo)
    label.image = photo #avoid garbage collection

image = tk.PhotoImage(file = 'img\\vazii.gif')
copy_of_image = image.copy()


 #Create Button to send message
SendButton = Button(root, font=("Verdana",10,'bold'), text="RESULTAT", width=8, height=3,

                     bd=5 , activebackground="#239EFD",fg='#000000',

                     command=pred )

###################################
l3 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
l3.config(state=DISABLED)

l1 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
l1.config(state=DISABLED)

l2 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
l2.config(state=DISABLED)

réponse1 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
réponse1.config(state=DISABLED)

photo = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
photo.config(state=DISABLED)

photo2 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
photo.config(state=DISABLED)

réponse2 = Text(root, bd=0, bg="white", height="8", width="50", font="Arial")
réponse2.config(state=DISABLED)

imgl3 = tk.PhotoImage(file = "img\\ner.gif")
imgl2 = tk.PhotoImage(file = "img\\intention.gif")
imgl = tk.PhotoImage(file = "img\\phrase1.gif")
img = tk.PhotoImage(file = "img\\vazii.gif")
img3 = tk.PhotoImage(file = "img\\icosnett.gif")
img2 = tk.PhotoImage(file = "img\\fb.gif")

entrée = Text(root, bd=0, bg="white",width="29", height="5", font="Arial")

l=Text(root,font = "italic",bg="#239EFD",fg='white')

réponse1.image_create(END, image=img)
photo.image_create(END, image=img3)
photo2.image_create(END, image=img2)
l1.image_create(END, image=imgl)
l2.image_create(END, image=imgl2)
l3.image_create(END, image=imgl3)


 #Bind scrollbar to Chat window
scrollbar = Scrollbar(root, command=réponse.yview, cursor="heart")
réponse['yscrollcommand'] = scrollbar.set

réponse.pack(padx = 20, pady = 20)
 #Create the box to enter message

scrollbar1 = Scrollbar(root, command=entrée.yview, cursor="heart")
entrée['yscrollcommand'] = scrollbar1.set

scrollbar2 = Scrollbar(root, command=réponse2.yview, cursor="heart")
réponse2['yscrollcommand'] = scrollbar2.set

scrollbar3 = Scrollbar(root, command=l.yview, cursor="heart")
l['yscrollcommand'] = scrollbar3.set


 #Place all components on the screen
photo.place(x=400,y=1, height=60, width=300)
photo2.place(x=310,y=1, height=60, width=80)
l1.place(x=0,y=63,height=62)
l.place(x=93,y=63,height=62, width=590)
scrollbar.place(x=685,y=128, height=60) ## le red -> hhh
scrollbar1.place(x=685,y=490, height=60) ## le red hhh
réponse1.place(x=0,y=1, height=60, width=300) ## la zone de rep
l2.place(x=0,y=128,height=60,width=92)
réponse.place(x=93,y=128, height=60, width=590) ## la zone de rep
réponse2.place(x=0,y=255, height=233, width=683) ## la zone de rep
l3.place(x=0,y=191,height=60, width=700)
scrollbar2.place(x=685,y=255, height=240)
entrée.place(x=90, y=490, height=60, width=594) #pour écrire la phrase
SendButton.place(x=0,y=490, height=60)

scrollbar3.place(x=685,y=62, height=60)

root.mainloop()
