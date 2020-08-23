go:-
hypothesis(Disease),
write('It is suggested that the patient has '),
write(Disease),
nl,
undo;
write('Sorry, the system is unable to identify the disease'),nl,undo.

hypothesis(dengue) :-
symptom(fever),
symptom(vomiting),
symptom(headache),
symptom(rash),
symptom(joint_pain),
symptom(eye_pain),
symptom(nose_bleed),
symptom(fatigue),

nl,
write('Advices and Sugestions:'),
nl,
write('1 : Drink plenty of fluids and get plenty of rest.'),
nl,
write('2: Maintain a clean and hygienic environment at home'),
nl,
write('3: Use mosquito repellents and nets'),
nl,
write('4: follow proper diet'),
nl,
write('Dengue is spread by the Aedes mosquito, which prefers biting during the day time (sunlight hours). Even a single mosquito bite can lead to dengue. Thus it is important to protect loved ones, both indoors and outdoors, especially during the day. Before stepping out, the use of personal repellents like Goodknight Fabric Roll-On (or the Goodknight cool gel/patches etc.) can prevent mosquito bites.Please do not sleep in open air and cover your full skin because'),
nl,!.
hypothesis(cold) :-
symptom(headache),
symptom(runny_nose),
symptom(sneezing),
symptom(sore_throat),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Tylenol'),
nl,
write('2: Panadol'),
nl,
write('3: Nasal spray'),
nl,
write('Please weare warm cloths because'),
nl,!.



hypothesis(influenza) :-
symptom(sore_throat),
symptom(fever),
symptom(headache),
symptom(chills),
symptom(body_ache),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Tamiflu'),
nl,
write('2: Panadol'),
nl,
write('3: Zanamivir'),
nl,
write('Please take a warm bath and do salt gargling because'),
nl,!.


hypothesis(typhoid) :-
symptom(headache),
symptom(abdominal_pain),
symptom(poor_appetite),
symptom(fever),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Chloramphenicol'),
nl,
write('2: Amoxicillin'),
nl,
write('3: Ciprofloxacin'),
nl,
write('4: Azithromycin'),
nl,
write('Please do complete bed rest and take soft diet because'),
nl,!.


hypothesis(chicken_pox) :-
symptom(rash),
symptom(body_ache),
symptom(fever),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Varicella vaccine'),
nl,
write('2: Immunoglobulin'),
nl,
write('3: Acetomenaphin'),
nl,
write('4: Acyclovir'),
nl,
write('Please do have oatmeal bath and stay at home because'),
nl.


hypothesis(measles) :-
symptom(fever),
symptom(runny_nose),
symptom(rash),
symptom(conjunctivitis),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Tylenol'),
nl,
write('2: Aleve'),
nl,
write('3: Advil'),
nl,
write('4: Vitamin A'),
nl,
write('Please get rest and use more liquid because'),
nl,!.

hypothesis(diabetes) :-
symptom(headache),
symptom(increased_hunger),
symptom(dry_mouth),
symptom(fatigue),
symptom(blurred_vision),
symptom(frequent_urination),
symptom(weightloss),
nl,
write('Advices and Sugestions:'),
nl,
write('1: Metformin '),
nl,
write('2: Sulfonylureas'),
nl,
write('3: Thiazolidinediones'),
nl,
write('4: DPP-4 inhibitors'),
nl,
write('4: GLP-1 receptor agonists'),
nl,
write('4: SGLT2 inhibitors'),
nl,
write('4: Insulin Therapy'),
nl,
write('Please check your blood sugar level at least twice a day.Control your blood pressure, cholesterol, and triglyceride levels. Get A1c blood tests to find out your average blood sugar for the past 2 to 3 months to track your carbohydrates because '),
nl,!.

ask(Question) :-
write('Does the patient has the symptom '),
write(Question),
write('? : '),
read(Response),
nl,
( (Response == yes ; Response == y)
->
assert(yes(Question)) ;
assert(no(Question)), fail).
:- dynamic yes/1,no/1.

symptom(S) :-
(yes(S)
->
true ;
(no(S)
->
fail ;
ask(S))).

undo :- retract(yes(_)),fail.
undo :- retract(no(_)),fail.
undo.
